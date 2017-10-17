-- this script is meant to truncate the Spring Batch-specific tables
begin ; 
truncate batch_job_execution cascade ; 
truncate batch_job_execution_context cascade; 
truncate batch_job_instance cascade; 
truncate batch_job_params cascade; 
truncate batch_step_execution cascade; 
truncate batch_step_execution_context cascade ;
commit; 
 