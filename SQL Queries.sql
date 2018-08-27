create table CLUSTER_INPUT_STAGE_2
as
select distinct least(id_1,id_2) as id_1,greatest(id_1,id_2) as id_2
from CLUSTER_INPUT_STAGE_1;
