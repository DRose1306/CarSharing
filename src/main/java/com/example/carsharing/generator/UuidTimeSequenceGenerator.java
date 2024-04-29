package com.example.carsharing.generator;

import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.UUID;

@RequiredArgsConstructor
public class UuidTimeSequenceGenerator implements IdentifierGenerator {

    private static final String NEXT_VAL_QUERY = "SELECT nextval('seq_for_uuid_generator');";
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long currTimeMillis = System.currentTimeMillis();

        return concatUUIDAndTime(currTimeMillis, UUID.randomUUID());
    }

    private UUID concatUUIDAndTime(long currTimeMillis, UUID uuid) {
        String millisHex = Long.toHexString(currTimeMillis);
        // Преобразование UUID в строку без дефисов и сокращение до первых 16 символов
        String uuidStr = uuid.toString().replace("-", "").substring(0, 16);
        // Форматирование строки для обеспечения фиксированной длины
        String concatenated = String.format("%016x%s", Long.parseLong(millisHex, 16), uuidStr);
        String concatenatedWithDashes = concatenated.substring(0, 8) + "-" +
                concatenated.substring(8, 12) + "-" +
                concatenated.substring(12, 16) + "-" +
                concatenated.substring(16, 20) + "-" +
                concatenated.substring(20);
        return UUID.fromString(concatenatedWithDashes);
    }


    /*private Long getSequenceValue() {
        return jdbcTemplate.queryForObject(NEXT_VAL_QUERY, (rs, rowNum) -> rs.getLong(1));
    }

    private char[] concatInHexFormat(long currTimeMillis, long sequenceValue) {
        char[] millisHex = Long.toHexString(currTimeMillis).toCharArray();
        char[] seqHex = Long.toHexString(sequenceValue).toCharArray();
        char[] concatenated = new char[36];

        System.arraycopy(millisHex, 0, concatenated, 0, millisHex.length);
        System.arraycopy(seqHex, 0, concatenated, 16, seqHex.length);

        return concatenated;
    }

    private String formatUuidToString(char[] uuid) {
        for (int i = 0; i < uuid.length; i++) {
            if (isDashPosition(i)) {
                System.arraycopy(uuid, i, uuid, i + 1, uuid.length - 1 - i);
                uuid[i] = '-';
            } else if (uuid[i] == 0) {
                uuid[i] = '0';
            }
        }
        return new String(uuid);
    }*/

    private boolean isDashPosition(int pos) {
        return pos == 8 || pos == 13 || pos == 18 || pos == 23;
    }
}