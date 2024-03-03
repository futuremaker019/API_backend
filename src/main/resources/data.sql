insert into user_account (user_id, user_password, nickname, email, role_types, created_by, created_at, modified_by, modified_at) values
('noah', '$2a$10$wCcynHi7x06kxPJqIJe.HuJdxcAJyyk1uwxUCJGf9tern2wnKPmnW', 'noah', 'noah@naver.com', 'USER,ADMIN', 'noah',  '2022-11-07 16:00:51', 'noah', '2022-11-07 16:00:51'),
('noah00o', '$2a$10$wCcynHi7x06kxPJqIJe.HuJdxcAJyyk1uwxUCJGf9tern2wnKPmnW', 'noah00o', 'noah00o@naver.com', 'USER,DEVELOPER','noah00o', '2022-11-07 16:00:51', 'noah00o', '2022-11-07 16:00:51'),
('john', '$2a$10$wCcynHi7x06kxPJqIJe.HuJdxcAJyyk1uwxUCJGf9tern2wnKPmnW', 'john', 'john@gmail.com', 'USER,MANAGER', 'john', '2022-11-07 16:00:51', 'john', '2022-11-07 16:00:51');

insert into article (created_at, created_by, modified_at, modified_by, content, title, account_id) values
('2023-07-03 23:23:40','noah00o','2023-06-01 18:01:26','noah00o','Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.','Lalmonirhat Airport',1),
('2022-11-06 20:07:00','noah00o','2023-05-29 04:23:02','noah','Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.','Albertus Airport',1),
('2023-03-14 08:34:47','john','2023-08-27 21:01:32','john','Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.','Bauerfield International Airport',1),
('2022-10-26 19:52:32','john','2022-10-14 15:48:05','john','Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem. Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.','San Antonio International Airport',1),
('2022-10-22 13:21:40','noah00o','2022-11-22 11:38:52','noah','Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.','Baures Airport',2),
('2022-12-12 02:08:41','noah00o','2022-10-29 10:41:04','noah','Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.','Rocha Airport',2),
('2022-09-22 21:56:52','noah','2023-09-08 18:42:56','noah00o','In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.','Ashgabat International Airport',2),
('2023-07-19 00:47:14','noah00o','2023-08-17 10:40:59','noah','Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.','Žilina Airport',2),
('2022-11-07 16:00:51','noah','2023-01-31 15:35:00','noah00o','Fusce consequat. Nulla nisl. Nunc nisl.','Maniitsoq Airport',2),
('2023-04-06 16:23:37','john','2022-09-17 11:08:17','john','Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.','Chachapoyas Airport',2),
('2022-09-29 00:58:20','john','2022-10-04 02:07:46','john','In congue. Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.','José Joaquín de Olmedo International Airport',2),
('2023-01-01 20:24:15','john','2023-02-23 07:22:48','john','Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.','Songwe Airport',2),
('2023-09-09 08:51:48','john','2023-03-22 17:35:15','noah','Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.','De Kooy Airport',2),
('2022-12-25 08:23:22','noah00o','2022-11-26 07:10:50','john','Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.','Pattaya Airpark',2),
('2023-08-25 20:50:19','noah00o','2023-03-03 02:02:27','noah00o','Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo. Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.','La Güera Airport',2),
('2023-03-29 00:35:19','john','2022-12-25 02:37:35','noah','Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius.','Apataki Airport',2),
('2022-12-21 00:13:52','noah','2023-01-17 10:52:03','john','Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Duis faucibus accumsan odio. Curabitur convallis.Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.','Alexandra Airport',2),
('2022-11-25 04:33:43','noah00o','2022-12-23 15:09:18','noah','Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.','Tidjikja Airport',2),
('2023-09-14 22:59:53','noah','2023-02-22 21:17:23','noah','Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.','Great Barrier Aerodrome',2),
('2023-01-04 16:42:05','noah','2023-04-26 03:39:14','noah00o','Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.In congue. Etiam justo. Etiam pretium iaculis justo.','Kherson International Airport',2),
('2023-01-02 22:08:20','john','2022-11-08 03:46:26','noah','Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Mauris viverra diam vitae quam. Suspendisse potenti.','Fallon Naval Air Station',2),
('2022-12-24 07:53:49','noah','2022-09-25 06:16:02','john','Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Duis faucibus accumsan odio. Curabitur convallis.','Saposoa Airport',2),
('2022-12-30 22:43:51','john','2023-09-01 10:59:24','john','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus. Praesent lectus.Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Duis faucibus accumsan odio. Curabitur convallis.','Mc Clellan-Palomar Airport',2),
('2023-09-10 03:44:11','noah00o','2023-03-07 11:33:16','noah','Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.In congue. Etiam justo. Etiam pretium iaculis justo.','Sintang(2, Susilo) Airport',2),
('2023-05-11 12:13:31','noah00o','2023-08-16 05:27:06','john','Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Mauris viverra diam vitae quam. Suspendisse potenti.','Herendeen Bay Airport',2),
('2022-12-26 05:26:56','noah00o','2023-03-03 11:56:54','noah00o','Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.','Myitkyina Airport',2),
('2023-01-09 12:03:38','john','2022-12-06 05:46:42','noah','In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Mauris viverra diam vitae quam. Suspendisse potenti.','Evans Head Aerodrome',2),
('2023-02-14 20:58:57','john','2022-12-17 18:14:45','john','Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.','Darnley Island Airport',2),
('2023-06-04 08:12:05','noah','2022-10-28 02:31:11','john','Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.','Papa Stour Airport',2),
('2023-03-16 22:46:19','noah','2023-03-02 11:15:21','noah00o','Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.','Streaky Bay Airport',2),
('2022-12-03 03:56:05','noah00o','2023-07-19 00:45:37','noah00o','Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.','Maitland Airport',2),
('2022-12-12 15:36:43','noah00o','2023-05-27 04:25:21','noah','Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.','West Memphis Municipal Airport',2),
('2023-03-30 09:00:02','john','2023-06-27 00:12:30','noah','Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.','Brigadier Antonio Parodi Airport',2),
('2023-03-10 00:44:22','john','2022-12-23 05:13:23','john','Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.','Oranjemund Airport',2),
('2022-12-06 17:56:47','noah00o','2022-09-22 17:43:42','noah00o','Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.','La Nubia Airport',2),
('2023-06-09 18:48:34','noah00o','2022-12-09 21:51:01','noah00o','Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.','Funter Bay Seaplane Base',2),
('2023-07-29 15:13:07','noah00o','2023-05-18 05:29:49','noah','Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.','Sanderson Field',2),
('2023-01-22 18:59:39','noah','2023-09-05 12:20:23','john','Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Mauris viverra diam vitae quam. Suspendisse potenti.Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris. Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.','Tok Junction Airport',2),
('2022-12-02 22:06:03','john','2023-02-16 22:48:38','noah00o','Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.','General Urquiza Airport',2),
('2023-02-03 16:21:02','john','2022-09-30 22:40:33','john','Sed ante. Vivamus tortor. Duis mattis egestas metus.Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.','Limoges Airport',2),
('2023-04-02 20:19:21','john','2022-09-23 15:18:21','john','Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.','Chigorodó Airport',2),
('2022-10-18 03:43:52','john','2023-02-09 22:42:07','noah00o','Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.','Balıkesir Merkez Airport',2),
('2023-09-10 13:41:48','john','2022-12-03 07:09:49','noah','Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.','Bazaruto Island Airport',2),
('2023-08-16 17:22:09','noah','2023-06-21 05:25:40','noah00o','Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.Phasellus in felis. Donec semper sapien a libero. Nam dui.','Lac Brochet Airport',2),
('2023-04-15 23:07:23','john','2023-08-09 18:22:36','noah','In congue. Etiam justo. Etiam pretium iaculis justo.','Bacău Airport',2),
('2023-09-03 20:22:37','noah','2023-03-26 16:19:00','noah','Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.','Santiago Perez Airport',2),
('2023-07-31 06:35:16','noah','2023-01-09 07:08:38','noah00o','Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.','Lawrenceville Vincennes International Airport',2),
('2023-06-13 08:51:51','noah','2022-12-29 14:40:37','noah00o','Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.','Mayor PNP Nancy Flores Paucar Airport',2),
('2023-08-10 08:35:54','noah00o','2022-12-30 13:27:45','john','Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.','Ikamiut Heliport',2),
('2023-07-20 13:32:22','noah','2023-05-21 02:19:11','noah','Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.','Lyndhurst Airport',2),
('2023-04-16 08:28:20','noah','2022-12-06 09:07:50','noah','Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.','Liuzhou Bailian Airport',2),
('2023-01-11 08:54:36','john','2023-01-11 19:30:05','noah','Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.','Nevşehir Kapadokya Airport',2),
('2022-11-17 23:30:58','john','2023-07-25 04:16:23','john','In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.','Montrose Regional Airport',2),
('2023-06-10 09:21:56','noah','2023-02-21 10:14:48','noah','Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.','RAF Greenham Common',2),
('2023-01-05 20:18:00','noah','2023-06-27 22:04:03','noah','Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.','Wondai Airport',2);

insert into article_comment (account_id, content, article_id, created_by, created_at, modified_by, modified_at) values (1, 'Proin risus.', 20, 3, '2023-03-07 18:33:10', 2, '2022-10-24 18:24:21')
 ,(1, 'Cras pellentesque volutpat dui.', 29, 2, '2023-07-27 23:17:58', 2, '2023-03-22 16:51:15')
 ,(1, 'Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum.', 11, 1, '2022-10-10 12:12:46', 1, '2023-05-14 06:49:16')
 ,(1, 'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', 28, 1, '2023-02-13 20:49:12', 3, '2022-09-28 02:16:05')
 ,(1, 'Fusce consequat.', 36, 1, '2023-06-20 21:40:57', 2, '2022-09-26 02:38:21')
 ,(1, 'Suspendisse potenti. Nullam porttitor lacus at turpis.', 40, 2, '2023-01-21 13:49:03', 3, '2023-06-22 22:02:22')
 ,(2, 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', 5, 2, '2023-01-03 01:14:08', 1, '2023-02-11 06:46:52')
 ,(2, 'Aliquam non mauris.', 35, 3, '2023-08-30 02:19:46', 1, '2023-04-25 07:50:14')
 ,(2, 'Nam dui.', 54, 1, '2022-12-26 10:06:20', 3, '2023-01-20 03:29:33')
 ,(2, 'Quisque ut erat.', 25, 3, '2022-12-20 16:05:13', 1, '2023-05-11 10:56:33')
 ,(2, 'Etiam vel augue. Vestibulum rutrum rutrum neque.', 47, 1, '2023-06-28 13:03:56', 2, '2022-11-01 23:04:52')
 ,(2, 'Integer ac neque.', 8, 1, '2023-05-26 03:36:59', 3, '2023-04-07 17:34:55')
 ,(2, 'Phasellus id sapien in sapien iaculis congue.', 6, 1, '2023-03-22 05:48:56', 1, '2023-08-22 16:14:41')
 ,(2, 'Suspendisse potenti. In eleifend quam a odio.', 16, 2, '2022-11-26 15:32:17', 3, '2022-12-21 17:11:42')
 ,(2, 'Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', 1, 3, '2023-08-01 00:06:55', 3, '2023-08-14 17:39:31')
 ,(2, 'Nulla nisl. Nunc nisl.', 16, 1, '2023-07-20 15:52:08', 3, '2023-05-31 03:56:31')
 ,(2, 'Curabitur at ipsum ac tellus semper interdum.', 31, 1, '2023-06-09 12:42:48', 3, '2022-12-25 14:12:53')
 ,(2, 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi.', 36, 1, '2023-06-21 20:20:53', 2, '2023-03-18 06:33:32')
 ,(2, 'Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.', 18, 1, '2023-04-22 02:12:24', 1, '2022-12-24 01:48:33')
 ,(2, 'Nulla ut erat id mauris vulputate elementum. Nullam varius.', 39, 3, '2023-09-01 18:36:33', 3, '2023-03-01 08:36:28')
 ,(2, 'Duis at velit eu est congue elementum. In hac habitasse platea dictumst.', 14, 1, '2022-12-26 18:14:48', 3, '2023-06-16 10:27:47')
 ,(2, 'In blandit ultrices enim.', 53, 3, '2022-12-11 02:27:18', 2, '2022-11-29 05:24:03')
 ,(2, 'Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 25, 3, '2023-04-19 01:55:28', 2, '2023-05-13 05:06:56')
 ,(2, 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi.', 26, 3, '2023-08-02 19:40:16', 1, '2023-05-20 20:32:42')
 ,(2, 'Integer non velit.', 13, 2, '2022-10-25 21:51:20', 3, '2023-06-30 11:30:54')
 ,(2, 'Aenean auctor gravida sem.', 23, 1, '2022-10-22 10:58:01', 3, '2023-02-03 08:43:35')
 ,(2, 'Integer ac leo. Pellentesque ultrices mattis odio.', 34, 2, '2023-09-14 02:17:25', 1, '2023-03-22 08:57:10')
 ,(2, 'Integer a nibh. In quis justo.', 52, 1, '2023-04-06 01:59:37', 2, '2023-04-24 15:58:08')
 ,(2, 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum.', 31, 3, '2023-01-16 18:43:04', 3, '2023-07-01 14:42:28')
 ,(2, 'Vivamus vel nulla eget eros elementum pellentesque.', 22, 3, '2022-12-06 01:03:13', 3, '2023-04-09 16:08:43')
 ,(2, 'Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.', 14, 1, '2023-02-21 18:14:54', 2, '2022-10-18 09:42:04')
 ,(2, 'Morbi porttitor lorem id ligula.', 45, 1, '2023-08-23 19:25:46', 2, '2023-05-01 12:28:07')
 ,(2, 'Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 7, 2, '2023-06-27 14:43:26', 1, '2023-07-09 03:11:21')
 ,(2, 'Ut at dolor quis odio consequat varius. Integer ac leo.', 13, 3, '2023-03-28 10:51:03', 3, '2023-05-14 22:27:23')
 ,(2, 'Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.', 21, 1, '2022-10-11 07:34:59', 2, '2022-12-07 19:09:21')
 ,(2, 'Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.', 24, 1, '2022-12-16 06:00:38', 3, '2022-11-20 05:05:50')
 ,(2, 'Suspendisse potenti. In eleifend quam a odio.', 30, 2, '2022-10-21 09:02:55', 3, '2022-11-27 14:57:47')
 ,(2, 'Maecenas pulvinar lobortis est. Phasellus sit amet erat.', 2, 3, '2022-09-29 07:11:33', 1, '2023-04-22 19:12:22')
 ,(2, 'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.', 26, 1, '2023-09-03 19:04:09', 1, '2022-12-11 18:04:59')
 ,(2, 'Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.', 24, 1, '2023-03-19 14:01:53', 2, '2023-01-23 21:50:10')
 ,(2, 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.', 54, 2, '2022-11-26 20:30:35', 1, '2022-10-11 08:58:09')
 ,(2, 'Suspendisse accumsan tortor quis turpis.', 33, 1, '2023-07-13 16:08:58', 3, '2023-07-09 14:53:48')
 ,(2, 'Ut tellus.', 40, 2, '2023-05-14 15:13:43', 2, '2023-05-28 11:29:33')
 ,(2, 'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', 31, 2, '2022-09-24 07:14:58', 2, '2023-09-15 16:38:32')
 ,(2, 'Cras pellentesque volutpat dui.', 39, 3, '2023-01-03 02:39:21', 3, '2023-03-22 04:13:36')
 ,(2, 'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 22, 2, '2023-09-02 02:47:51', 2, '2023-01-07 06:04:02')
 ,(2, 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum.', 3, 1, '2023-08-22 18:43:44', 1, '2022-12-25 01:53:04')
 ,(2, 'Etiam faucibus cursus urna. Ut tellus.', 38, 1, '2022-11-30 18:02:55', 3, '2022-10-29 08:53:23')
 ,(2, 'Integer tincidunt ante vel ipsum.', 29, 3, '2023-06-23 04:22:42', 2, '2023-05-14 11:40:15')
 ,(2, 'Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.', 17, 2, '2023-04-18 15:32:24', 2, '2023-04-06 13:49:22')
 ,(2, 'Pellentesque ultrices mattis odio. Donec vitae nisi.', 2, 3, '2023-04-11 05:33:50', 2, '2023-09-15 04:50:49')
 ,(2, 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.', 49, 3, '2023-01-17 18:30:38', 1, '2022-12-26 11:28:22')
 ,(2, 'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.', 45, 2, '2023-07-04 19:25:17', 1, '2023-01-02 11:25:03')
 ,(2, 'Suspendisse accumsan tortor quis turpis.', 6, 2, '2022-12-14 09:11:47', 1, '2023-03-19 14:22:44')
 ,(2, 'Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 38, 2, '2023-04-06 00:03:50', 1, '2022-12-20 09:56:42')
 ,(2, 'Morbi non quam nec dui luctus rutrum.', 8, 1, '2023-06-16 16:48:24', 2, '2023-02-17 22:31:36')
 ,(2, 'Nunc nisl.', 13, 3, '2022-12-05 22:16:20', 3, '2022-10-02 16:11:11')
 ,(2, 'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.', 41, 2, '2023-08-21 14:01:08', 1, '2023-07-22 22:46:28')
 ,(2, 'Duis mattis egestas metus.', 17, 2, '2023-09-01 22:04:07', 3, '2022-12-29 08:04:29')
 ,(2, 'Duis bibendum.', 17, 2, '2022-10-29 13:38:57', 2, '2023-04-07 02:19:09')
 ,(2, 'In quis justo.', 40, 2, '2023-04-14 08:59:06', 1, '2023-07-24 16:52:40')
 ,(2, 'Morbi ut odio.', 16, 1, '2023-03-29 02:31:27', 3, '2023-02-27 09:53:18')
 ,(2, 'Praesent lectus.', 44, 3, '2023-03-11 23:36:10', 1, '2023-07-21 23:27:25')
 ,(2, 'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat.', 12, 2, '2023-01-26 03:22:42', 3, '2022-11-05 23:47:31')
 ,(2, 'Nullam varius.', 32, 1, '2023-04-10 00:24:05', 1, '2023-03-31 11:05:47')
 ,(2, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.', 42, 2, '2023-01-22 17:21:36', 2, '2022-10-20 23:38:48')
 ,(2, 'Nulla mollis molestie lorem. Quisque ut erat.', 45, 3, '2023-06-16 07:09:00', 1, '2022-12-25 10:56:46')
 ,(2, 'Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet.', 49, 3, '2023-06-14 18:48:32', 1, '2023-06-26 21:52:48')
 ,(2, 'Nulla ut erat id mauris vulputate elementum. Nullam varius.', 38, 2, '2023-08-22 18:47:51', 1, '2023-03-24 10:01:14')
 ,(2, 'Donec posuere metus vitae ipsum.', 50, 3, '2023-02-02 21:25:42', 2, '2023-07-16 16:03:32')
 ,(2, 'Phasellus sit amet erat.', 39, 1, '2022-12-11 19:28:57', 1, '2023-07-23 18:00:36')
 ,(2, 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae Nulla dapibus dolor vel est.', 55, 3, '2022-12-21 19:27:06', 1, '2023-03-10 15:31:39')
 ,(2, 'Pellentesque eget nunc.', 41, 3, '2022-12-20 02:21:36', 1, '2022-12-27 04:14:01')
 ,(2, 'Duis aliquam convallis nunc.', 1, 1, '2022-10-27 14:00:18', 1, '2023-06-08 03:59:22')
 ,(2, 'Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', 48, 3, '2023-01-06 19:50:51', 1, '2023-09-11 16:20:33')
 ,(2, 'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', 34, 2, '2022-12-06 08:38:24', 3, '2022-12-30 08:43:17')
 ,(2, 'Integer non velit.', 1, 2, '2023-05-09 08:01:35', 1, '2022-09-24 03:16:07')
 ,(2, 'Etiam pretium iaculis justo.', 5, 3, '2023-04-02 21:23:43', 2, '2023-03-30 04:12:47')
 ,(2, 'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus.', 18, 1, '2023-03-09 13:22:06', 2, '2023-03-02 23:31:44')
 ,(2, 'Etiam vel augue.', 30, 3, '2023-05-14 09:10:03', 2, '2022-12-24 06:07:12')
 ,(2, 'In hac habitasse platea dictumst. Etiam faucibus cursus urna.', 50, 1, '2023-06-11 23:19:15', 1, '2022-12-28 12:35:27')
 ,(2, 'Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.', 1, 1, '2022-11-26 19:33:22', 2, '2023-01-18 02:31:12')
 ,(2, 'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam. Nam tristique tortor eu pede.', 53, 2, '2023-03-07 11:06:41', 1, '2023-03-07 15:53:16')
 ,(2, 'Vestibulum sed magna at nunc commodo placerat.', 41, 1, '2023-02-16 19:31:46', 2, '2023-06-12 16:16:06')
 ,(2, 'Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 52, 2, '2022-10-29 14:16:02', 2, '2022-11-24 13:46:01')
 ,(2, 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', 16, 3, '2022-09-26 00:29:16', 1, '2023-08-25 06:54:10')
 ,(2, 'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.', 21, 2, '2023-02-20 11:28:56', 3, '2023-05-28 08:07:14')
 ,(2, 'Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.', 44, 1, '2023-07-03 22:41:40', 1, '2023-05-21 15:55:57')
 ,(2, 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa.', 27, 3, '2022-09-27 14:47:05', 1, '2023-05-09 01:28:59')
 ,(2, 'Proin at turpis a pede posuere nonummy.', 32, 3, '2023-06-16 12:10:57', 3, '2023-02-06 18:22:14')
 ,(2, 'Donec quis orci eget orci vehicula condimentum.', 54, 3, '2023-02-20 15:44:14', 1, '2023-02-18 09:05:31')
 ,(2, 'Integer ac leo. Pellentesque ultrices mattis odio.', 18, 3, '2023-06-04 11:48:36', 1, '2022-10-17 08:33:22')
 ,(2, 'Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', 54, 2, '2023-02-22 13:09:04', 3, '2022-11-06 17:54:54')
 ,(2, 'In congue. Etiam justo.', 25, 2, '2022-12-03 16:27:08', 2, '2023-02-26 14:13:44')
 ,(2, 'In quis justo. Maecenas rhoncus aliquam lacus.', 31, 3, '2022-10-29 15:32:03', 3, '2022-10-21 21:23:28')
 ,(2, 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.', 51, 3, '2023-09-14 13:29:37', 1, '2023-02-19 07:30:40')
 ,(2, 'In est risus, auctor sed, tristique in, tempus sit amet, sem.', 16, 1, '2022-10-15 19:10:16', 2, '2023-09-03 06:05:50')
 ,(2, 'In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.', 54, 2, '2022-10-28 00:37:24', 2, '2023-01-04 09:54:32')
 ,(2, 'Nam nulla.', 31, 2, '2023-08-01 02:54:00', 1, '2023-03-02 16:55:25')
 ,(2, 'Pellentesque at nulla.', 25, 2, '2023-02-27 15:20:55', 3, '2023-03-29 18:15:33');

insert into code (`name`, parent_id, icon, user_id, created_by, created_at, modified_by, modified_at) values
('플랫폼', null, 'PresentationChartBarIcon', 1, 1,  '2023-03-07 18:33:10', 2, '2023-10-24 18:24:21'),
('채용전형', null, 'FlagIcon', 2, 3, '2023-03-07 18:33:10', 1, '2023-10-24 18:24:21'),
('잡코리아', 1, null, 3, 3,  '2023-03-08 18:33:10', 1, '2023-10-24 18:24:21'),
('사람인', 1, null, 1, 3,  '2023-03-09 18:33:10', 1, '2023-10-24 18:24:21'),
('잡플래닛', 1, null, 2, 3,  '2023-03-10 18:33:10', 1, '2023-10-24 18:24:21'),
('서류전형', 2, null, 3, 3,  '2023-03-11 18:33:10', 1, '2023-10-24 18:24:21'),
('코딩테스트', 2, null, 1, 3,  '2023-03-12 18:33:10', 1, '2023-10-24 18:24:21'),
('1차 화상면접', 6, null, 2, 3,  '2023-03-13 18:33:10', 1, '2023-10-24 18:24:21'),
('2차 화상면접', 8, null, 3, 3,  '2023-03-14 18:33:10', 1, '2023-10-24 18:24:21'),
('최종합격', 9, null, 1, 3,  '2023-03-15 18:33:10', 1, '2023-10-24 18:24:21'),
('기술면접', 7, null, 2, 3,  '2023-03-16 18:33:10', 1, '2023-10-24 18:24:21'),
('1차 AI 면접', 6, null, 3, 3,  '2023-03-17 18:33:10', 1, '2023-10-24 18:24:21'),
('2차 AI 면접', 12, null, 1, 3,  '2023-03-18 18:33:10', 1, '2023-10-24 18:24:21');