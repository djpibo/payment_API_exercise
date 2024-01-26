DROP TABLE IF EXISTS MEMBER;
CREATE TABLE MEMBER COMMENT '회원정보'
(
    id                INTEGER NOT NULL,
    birth             VARCHAR(50) NOT NULL,
    buyer_name        VARCHAR(50) NOT NULL,
    buyer_tel         VARCHAR(50) NOT NULL,
    buyer_addr        VARCHAR(50) NOT NULL,
    buyer_postcode    VARCHAR(50) NOT NULL,
    status            CHAR(50) NOT NULL,
    created_date      TIMESTAMP,
    lastModified_date TIMESTAMP,
    created_by        VARCHAR(50),
    lastModified_by   VARCHAR(50),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS USER_CARD;
CREATE TABLE USER_CARD COMMENT '회원보유카드정보'
(
    id                INTEGER NOT NULL,
    user_id           INTEGER NOT NULL,
    card_number       VARCHAR(50) NOT NULL,
    expiry            VARCHAR(8) NOT NULL,
    pwd_2digit        VARCHAR(8) NOT NULL,
    cvc               VARCHAR(3) NOT NULL,
    status            VARCHAR(50) NOT NULL,
    created_date      TIMESTAMP,
    lastModified_date TIMESTAMP,
    created_by        VARCHAR(50),
    lastModified_by   VARCHAR(50),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS CARD_ORDER;
CREATE TABLE CARD_ORDER COMMENT '주문정보'
(
    id                       INTEGER NOT NULL,
    card_id                  INTEGER NOT NULL,
    user_id                  INTEGER NOT NULL,
    merchant_uid             VARCHAR(50) NOT NULL,
    amount                   DECIMAL NOT NULL,
    tax_free                 DECIMAL NOT NULL,
    vat_amount               DECIMAL NOT NULL,
    customer_uid             INTEGER NOT NULL,
    pg                       VARCHAR(50) NOT NULL,
    name                     VARCHAR(50) NOT NULL,
    custom_data              VARCHAR(50) NOT NULL,
    browser_ip               VARCHAR(50) NOT NULL,
    notice_url               VARCHAR(50) NOT NULL,
    secure_3d_charge_id      VARCHAR(50) NOT NULL,
    secure_3d_token          VARCHAR(50) NOT NULL,
    product_type             VARCHAR(50) NOT NULL,
    card_quota               VARCHAR(50) NOT NULL,
    use_card_point           BOOLEAN NOT NULL,
    interst_free_by_merchant BOOLEAN NOT NULL,
    status                   CHAR(50) NOT NULL,
    created_date             TIMESTAMP,
    lastModified_date        TIMESTAMP,
    created_by               VARCHAR(50),
    lastModified_by          VARCHAR(50),
    PRIMARY KEY (id)
);