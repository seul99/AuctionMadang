use auction;
#user에 data 추가
INSERT INTO `user` (nickname, user_id, password, address, category, email, phone_number, bank, account_no, intro, profile_image, is_deleted) VALUES
                                                                                                                                                 ('홍길동', 'HongGillDong', 'hong12', NULL, NULL, NULL, '000-1111-2222', '우리은행', '111-222', NULL, NULL, false),
                                                                                                                                                 ('김영희', 'KimYoungHee', 'kim12', NULL, NULL, NULL, NULL, '카카오뱅크', '222-333', NULL, NULL, false),
                                                                                                                                                 ('이철수', 'LeeChulSoo', 'lee123', '대구광역시 수성구', NULL, 'lee@example.com', '010-3456-7890', '신한은행', '333-444', NULL, NULL, false),
                                                                                                                                                 ('박민수', 'ParkMinSoo', 'minsoo!', '경기도 성남시 분당구', NULL, 'park@example.com', '010-4567-8901', '하나은행', '444-555', NULL, NULL, false),
                                                                                                                                                 ('최지우', 'ChoiJiWoo', 'choipw', '인천광역시 연수구', NULL, 'choi@example.com', '010-5678-9012', '토스뱅크', '555-666', NULL, NULL, false);

#category에 data 추가
INSERT INTO category(category_name) VALUES
                                        ('디지털 기기'),
                                        ('가구/인테리어'),
                                        ('유아동 물품'),
                                        ('여성의류'),
                                        ('여성잡화'),
                                        ('남성패션/잡화'),
                                        ('생활가전'),
                                        ('취미/게임/음반'),
                                        ('뷰티/미용');

INSERT INTO user_interest_category (user_id, category_id) VALUES
                                                              ('HongGillDong', 1),
                                                              ('HongGillDong', 2),
                                                              ('KimYoungHee', 2),
                                                              ('KimYoungHee', 3);


INSERT INTO product(deadline, product_price, user_id, description, name, status, category_id, image_path, `condition`, created_at) VALUES
                                                                                                                                       ('2025-06-10',50000,'HongGillDong', '거의 새것 같은 디지털 카메라입니다.', '디지털 카메라', 'ON_SALE', 1, NULL,'LIKE_NEW', NOW()),
                                                                                                                                       ('2025-07-10', 150000,'HongGillDong', '4인용 고급 소파입니다.', '소파', 'ON_SALE', 2, NULL, 'LIGHTLY_USED', NOW()),
                                                                                                                                       ('2025-07-01', 20000,  'KimYoungHee', '아기 옷과 장난감 세트 함께 팔아요', '아기옷 & 장난감', 'SOLD_OUT', 3, NULL, 'HEAVILY_USED', NOW());


INSERT INTO Wishlist (user_id, product_id) VALUES
                                               ('HongGillDong', 1),
                                               ('KimYoungHee', 2),
                                               ('LeeChulSoo', 3);




# INSERT INTO `order`(buyer_id, order_status, shipping_address, product_id, receiver_name, receiver_phone, ordered_at) VALUES
#                                                                                                                          ('HongGillDong', 'PURCHASED', '서울특별시 강남구 역삼동 123-45', 1, '홍길동', '010-1111-2222', NOW()),
#                                                                                                                          ('KimYoungHee', 'PURCHASED', '부산광역시 해운대구 88-77', 2, '김영희', '010-2222-3333', NOW()),
#                                                                                                                          ('LeeChulSoo', 'CANCELED', '대구광역시 수성구 동대구로 123', 3, '이철수', '010-3333-4444', NOW());

# desc `order`;

#Address 부분 data 추가
INSERT INTO Address (user_id, receiver_name, receiver_phone, zipcode, address, is_default) VALUES
                                                                                               ('HongGillDong', '홍길동', '010-1111-2222', '06234', '서울특별시 강남구 테헤란로 123', TRUE),
                                                                                               ('HongGillDong', '홍길동', '010-1111-2222', '06235', '서울특별시 서초구 반포대로 101', FALSE),
                                                                                               ('KimYoungHee', '김영희', '010-2222-3333', '48059', '부산광역시 해운대구 센텀중앙로 55', TRUE),
                                                                                               ('LeeChulSoo', '이철수', '010-3333-4444', '42035', '대구광역시 수성구 달구벌대로 1234', TRUE),
                                                                                               ('ParkMinSoo', '박민수', '010-4567-8901', '13595', '경기도 성남시 분당구 판교로 242', TRUE),
                                                                                               ('ChoiJiWoo', '최지우', '010-5678-9012', '21987', '인천광역시 연수구 송도과학로 27', TRUE);



INSERT INTO notice (title, content, type, created_at) VALUES
                                                          ('서버 점검 안내', '시스템 안정화를 위한 서버 점검이 6월 5일 새벽 1시부터 3시까지 진행됩니다.', 'SERVICE_INFO', NOW()),
                                                          ('신규 회원 이벤트!', '신규 가입 시 5,000포인트를 드리는 이벤트를 진행 중입니다.', 'EVENT', NOW()),
                                                          ('중요 공지사항', '개인정보 처리방침이 2025년 7월 1일부터 변경됩니다. 자세한 내용은 공지사항을 확인하세요.', 'NOTICE', NOW()),
                                                          ('여름 맞이 이벤트', '7월 한 달간 전 카테고리 무료배송 이벤트를 진행합니다!', 'EVENT', NOW()),
                                                          ('고객센터 운영시간 변경', '고객센터 운영시간이 오전 9시 ~ 오후 6시로 변경됩니다.', 'SERVICE_INFO', NOW());


INSERT INTO faq (question, answer, category) VALUES
                                                 ('회원 탈퇴는 어떻게 하나요?', '마이페이지 > 계정설정 > 회원탈퇴에서 진행하실 수 있습니다.', 'POLICY'),
                                                 ('상품을 구매했는데 배송이 오지 않아요.', '판매자에게 문의해보시고, 해결되지 않으면 고객센터로 연락주세요.', 'PURCHASE'),
                                                 ('상품 등록 시 이미지를 몇 장까지 올릴 수 있나요?', '최대 5장까지 등록 가능합니다.', 'SALE'),
                                                 ('구매 후 환불은 어떻게 하나요?', '구매내역에서 환불 요청을 선택하시면 됩니다.', 'PURCHASE'),
                                                 ('판매 수수료는 얼마인가요?', '판매 금액의 5%가 수수료로 부과됩니다.', 'SALE'),
                                                 ('부적절한 사용자는 어떻게 신고하나요?', '프로필 옆 신고 버튼을 통해 신고하실 수 있습니다.', 'POLICY');

