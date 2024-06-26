INSERT INTO registrable_member(name,reg_no) VALUES ('동탁','$2a$10$ETlTXpxhW6yO4v.P3yjyQe6Kgc0QHkyeAs8RFL6PpQPbrshRZ0XIS');
INSERT INTO registrable_member(name,reg_no) VALUES ('관우','$2a$10$3i2teVds987.6FoPh7FdgOu6KvmqC1z36u7lqYfPTDUfIN8pS89MG');
INSERT INTO registrable_member(name,reg_no) VALUES ('손권','$2a$10$hbUcsIkt9skRb4vWullmoO2jqZFkkvOiZ6EZC7feolOVhUlvrX8uS');
INSERT INTO registrable_member(name,reg_no) VALUES ('유비','$2a$10$bYWsU4FbxX.TwKuJNnCF/.bcvs15awmE4WZrsJkjUx6/aFMse8MyC');

INSERT INTO tax(tax_base_min,tax_base_max,add_tax,basic_tax_rate) VALUES (0,14000000,0,6);
INSERT INTO tax(tax_base_min,tax_base_max,add_tax,basic_tax_rate) VALUES (14000000,50000000,840000,15);
INSERT INTO tax(tax_base_min,tax_base_max,add_tax,basic_tax_rate) VALUES (50000000,88000000,6240000,24);
INSERT INTO tax(tax_base_min,tax_base_max,add_tax,basic_tax_rate) VALUES (88000000,150000000,15360000,35);
INSERT INTO tax(tax_base_min,tax_base_max,add_tax,basic_tax_rate) VALUES (150000000,300000000,37060000,38);
INSERT INTO tax(tax_base_min,tax_base_max,add_tax,basic_tax_rate) VALUES (300000000,500000000,94060000,40);
INSERT INTO tax(tax_base_min,tax_base_max,add_tax,basic_tax_rate) VALUES (500000000,1000000000,174060000,42);
INSERT INTO tax(tax_base_min,tax_base_max,add_tax,basic_tax_rate) VALUES (1000000000,2100000000,384060000,45);
