-- liquibase formatted sql
-- changeset author:hveiled:create-gibdd-table
CREATE TABLE vehicle_registration_numbers (
      id                BIGSERIAL,
      prefix            VARCHAR(1),
      numeric_value     VARCHAR(3),
      postfix           VARCHAR(2),
      granted_number    VARCHAR(16),
      PRIMARY KEY (id)
);
-- rollback DROP TABLE vehicle_registration_numbers

--changeset nvoxland:2
-- Select nextval(pg_get_serial_sequence('vehicle_registration_numbers', 'id')) as new_id;


-- INSERT INTO vehicle_registration_numbers (id, prefix, numeric_value, postfix, granted_number)
-- VALUES (1, 'Е', '777', 'КХ', 'Е777КХ 116 RUS');