SELECT parent_ID FROM parent_list WHERE contact_Nr = 9248;

SELECT * FROM parent_list p , admit_children_list a WHERE p.parent_ID = a.parent_ID;

select * FROM admit_children_list;

UPDATE admit_Children_List SET first_Name = 'Hey', last_Name = 'aascsdsdc', birth_Day = '2022-04-15',
parent_ID = '2' WHERE id IN ( SELECT id FROM ( SELECT id FROM admit_Children_List WHERE first_Name = 'aadwsvs'
    AND last_Name = 'aascsdsdc' AND birth_Day = '2022-04-15' AND parent_Id = '2') AS id);