INSERT INTO `usuarios` (`idUsuario`, `nombre`, `apellidos`, `correo`, `contrasena`) VALUES
('1', 'Raul', 'Hinojosa Perez', 'raul@gmail.com', 'qwerty1234'),
('2', 'Lila', 'Sarfson Gutierrez', 'lila@gmail.com', 'asdf*'),
('3', 'Arantxa', 'Delgado Porras', 'arantxa@gmail.com', 'ara123#'),
('4', 'Lidia', 'Alvarez Vaello', 'lidia@gmail.com', 'pass1234'),
('5', 'Toni', 'Serrano Jimenez', 'toni@gmail.com', 'toni123qwe'),
('6', 'Almudena', 'Raya Alcaide', 'arantxa@gmail.com', 'ara123#'),
('7', 'Miriam', 'Feijoo Lorden', 'miriam@gmail.com', 'Aqwer12');

INSERT INTO `viajes` (`idViaje`, `nombre`, `descripcion`, `fechaInicio`, `fechaFin`, `Usuarios_idUsuario`) VALUES
('1', 'Tenerife', 'Por fin... Una semanita en las Canarias ;-;', '2022-08-12', '2022-08-19', '1'),
('2', 'Paises Bajos', 'Amsterdam.', '2022-11-08', '2022-11-12', '3'),
('3', 'Islandia', 'Viaje de ensueño con mi pareja, una semanita llena de aventuras!', '2023-03-08', '2023-03-20', '7'),
('4', 'Puerto Rico', '1 mes de buena vida ;)', '2023-06-02', '2023-07-05', '4'),
('5', 'Francia', 'Baguette.', '2022-09-16', '2022-09-22', '4'),
('6', 'Around the world!', 'Ruta mochilera', '2022-07-29', '2023-09-09', '5'),
('7', 'Rusia', 'A pasar frio y a beber vodka, blyat!', '2022-11-30', '2022-12-08', '6'),
('8', 'USA', 'Espero no morir en un super tiroteado jej....', '2022-12-29', '2023-01-05', '2');

INSERT INTO `itemsviaje` (`idItemsViaje`, `nombre`, `descripcion`, `hora`, `precio`, `ubicacionLatitud`, `ubicacionLongitud`, `Viajes_IdViaje`) VALUES
('1', 'Guigui', 'Playa preciosa de arena negra', '16:00:00', '0', '28.302463', '-16.596816', '1'),
('2', 'Santa Cruz de Tenerife', 'Capital de tenerife', '10:00:00', '0', '28.302463', '-16.596816', '1'),
('3', 'Amsterdam', 'If you know you know...', '10:00:00', '0', '52.397401', '4.975370', '2'),
('4', 'Eindhoven', 'Pa hacer algo más que...', '10:00:00', '0', '52.397401', '4.975370', '2'),
('5', 'Reikiavik', '', '10:00:00', '0', '64.731076', '-18.343358', '3'),
('6', 'Parque nacional Vatnajökull', '', '16:00:00', '0', '64.731076', '-18.343358', '3'),
('7', 'San Juan', '', '20:00:00', '0', '18.238976', '-66.465175', '4'),
('8', 'Ponce', '', '12:00:00', '0', '18.238976', '-66.465175', '4'),
('9', 'Torre Eiffel', 'La torre más famosa de Francia (wow)', '16:00:00', '0', '48.857601', '2.293286', '5'),
('10', 'Petra, Jordania', 'Ciudad de Petra, una maravilla del mundo', '11:30:00', '0', '30.327954', '35.444448', '6'),
('11', 'Moscu', 'Frio', null, '0', '55.761484', '38.613550', '7'),
('12', 'San Petersburgo', 'Frio x2', null, '0', '59.849709', '30.534222', '7'),
('13', 'San Francisco', '', null, '0', '37.786733', '-122.353460', '8'),
('14', 'Miami', '', null, '0', '25.755169', '-80.214522', '8');