INSERT INTO public.role(
	id, role, creation_date)
	VALUES ('c1bb5e0f-2525-47b2-9bad-b4c5f160e036', 'USER', '2022-01-03');
INSERT INTO public.role(
	id, role, creation_date)
	VALUES ('802c28ae-920e-44d9-b74f-7a59739c67b4', 'ADMIN', '2022-01-03');
	
INSERT INTO public."user"(
	id, username, password, active, creation_date)
	VALUES ('ccc40b60-6e0e-4b11-82d1-274a1696e80c', 'zars', 'Welcome1', true, '2022-01-03');
	
INSERT INTO public.userrole(
	user_id, role_id)
	VALUES ('ccc40b60-6e0e-4b11-82d1-274a1696e80c', 'c1bb5e0f-2525-47b2-9bad-b4c5f160e036');
INSERT INTO public.userrole(
	user_id, role_id)
	VALUES ('ccc40b60-6e0e-4b11-82d1-274a1696e80c', '802c28ae-920e-44d9-b74f-7a59739c67b4');
	
	