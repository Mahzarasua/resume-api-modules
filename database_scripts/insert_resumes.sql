INSERT INTO public.resume(
	id, first_name, last_name, title, city, state, country, email, phone, summary, creation_date)
	VALUES ('1d84b77d-7670-4a62-adc1-5de5b24cb75d'
			, 'Miguel Alejandro'
			, 'Hernandez Zarasua'
			, 'Backend Developer'
			, 'Colima'
			, 'Colima'
			, 'Mexico'
			, 'mahzarasua@outlook.com'
			, '3121557091'
			, 'Java developer'
			, '2022-01-03');
			
INSERT INTO public.skill(
	id, name, percentage, type, resume_id)
	VALUES ('94e0e1ea-b7cc-4144-addd-5def8d0d572d', 'java', 85, 'tech skill','1d84b77d-7670-4a62-adc1-5de5b24cb75d');
INSERT INTO public.skill(
	id, name, percentage, type, resume_id)
	VALUES ('beed7158-c673-4170-8437-9cfb590f372c', 'Spanish (native)', 90, 'language skill','1d84b77d-7670-4a62-adc1-5de5b24cb75d');
INSERT INTO public.skill(
	id, name, percentage, type, resume_id)
	VALUES ('d3388539-6ede-4ade-a4bf-99653dbe5ff5', 'English', 80, 'language skill','1d84b77d-7670-4a62-adc1-5de5b24cb75d');
	
INSERT INTO public.school(
	id, name, career, start_date, end_date, degree, resume_id)
	VALUES ('96a970f1-7395-4e91-85ff-685915e3eaaf'
			, 'Instituto Tecnologico de Lazaro Cardenas'
			, 'Computer Systems Engineering'
			, '2016-08-01', '2021-12-01'
			, 'Bachelor'
			, '1d84b77d-7670-4a62-adc1-5de5b24cb75d');
			
INSERT INTO public.work_experience(
	id, title, company, start_date, end_date, current_job, description, resume_id)
	VALUES ('268fd1cd-8566-4bea-b170-01f53b3aae91'
			, 'Backend Developer'
			, '4th Source'
			, '2020-11-01'
			, null
			, true
			, 'Design and develop the backend for the enterprise projects.'
			, '1d84b77d-7670-4a62-adc1-5de5b24cb75d');
			
