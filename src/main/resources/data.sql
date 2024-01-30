INSERT INTO MEMBER (
    id,
    birth,
    buyer_name,
    buyer_tel,
    buyer_addr,
    buyer_postcode,
    status,
    created_date,
    lastModified_date,
    created_by,
    lastModified_by
)
VALUES
    (1, '19900101', 'John Doe', '123-456-7890', '123 Main St', '12345', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
    (2, '19851215', 'Jane Smith', '987-654-3210', '456 Oak St', '54321', 'INACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
    (3, '19780325', 'Bob Johnson', '555-123-4567', '789 Pine St', '67890', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');

INSERT INTO USER_CARD (id, card_number, expiry, pwd_2digit, cvc, status, created_date, lastModified_date, created_by, lastModified_by)
VALUES
(1, '1234567890123456', '122023', '123', '123', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
(2, '9876543210987654', '062024', '456', '456', 'INACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
(3, '1111222233334444', '092022', '789', '789', 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');

INSERT INTO CARD_ORDER (
    id,
    merchant_uid,
    amount,
    tax_free,
    vat_amount,
    customer_uid,
    pg,
    name,
    custom_data,
    browser_ip,
    notice_url,
    secure_3d_charge_id,
    secure_3d_token,
    product_type,
    card_quota,
    use_card_point,
    interst_free_by_merchant,
    status,
    created_date,
    lastModified_date,
    created_by,
    lastModified_by
)
VALUES
    (1, 'order123', 50000.00, 0.00, 5000.00, 1, 'KakaoPay', 'Product 1', 'custom_data_1', '127.0.0.1', 'http://example.com/notice', 'secure_charge_123', 'secure_token_123', 'Digital', '3', true, false, 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
    (2, 'order456', 75000.00, 0.00, 7500.00, 2, 'NaverPay', 'Product 2', 'custom_data_2', '192.168.0.1', 'http://example.com/notice2', 'secure_charge_456', 'secure_token_456', 'Physical', '6', false, true, 'COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin'),
    (3, 'order789', 100000.00, 0.00, 10000.00, 3, 'PayPal', 'Product 3', 'custom_data_3', '10.0.0.1', 'http://example.com/notice3', 'secure_charge_789', 'secure_token_789', 'Digital', '12', true, true, 'FAILED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'admin', 'admin');
