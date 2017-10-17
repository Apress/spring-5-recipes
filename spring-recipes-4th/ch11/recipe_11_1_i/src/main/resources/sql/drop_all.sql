begin;

drop table batch_job_execution cascade ; 
drop table batch_job_execution_context cascade; 
drop table batch_job_instance cascade; 
drop table batch_job_params cascade; 
drop table batch_step_execution cascade; 
drop table batch_step_execution_context cascade ;

DROP SEQUENCE batch_job_execution_seq;
DROP SEQUENCE batch_job_seq; 
DROP SEQUENCE batch_step_execution_seq;

commit; 