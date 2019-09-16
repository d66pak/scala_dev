#!/bin/bash

set -e

aws emr create-cluster \
	--applications Name=Hadoop Name=Sqoop \
	--ec2-attributes '{"KeyName":"Development","InstanceProfile":"EMR_EC2_DefaultRole","ServiceAccessSecurityGroup":"sg-057e2a4d174d123ec","SubnetId":"subnet-0055c33acc57f72bc","EmrManagedSlaveSecurityGroup":"sg-03363018327a849b1","EmrManagedMasterSecurityGroup":"sg-06adb49efcb317375"}' \
	--release-label emr-5.25.0 \
	--log-uri 's3n://aws-logs-167856709162-ap-southeast-2/elasticmapreduce/' \
	--steps '[{"Args":["s3://murali.test/install-redshift-driver.sh"],"Type":"CUSTOM_JAR","ActionOnFailure":"TERMINATE_CLUSTER","Jar":"s3://ap-southeast-2.elasticmapreduce/libs/script-runner/script-runner.jar","Properties":"","Name":"Install Redshift driver"},{"Args":["s3://murali.test/sqoop-import-redshift.sh","bicluster.cojsd0ru8xz.ap-southeast-2.redshift.amazonaws.com:5439/dev","username","password","staging.customerextended_test","s3://murali.test/sqoop-test/"],"Type":"CUSTOM_JAR","ActionOnFailure":"TERMINATE_CLUSTER","Jar":"s3://ap-southeast-2.elasticmapreduce/libs/script-runner/script-runner.jar","Properties":"","Name":"Sqoop from Redshift"}]' \
	--instance-groups '[{"InstanceCount":1,"BidPrice":"0.036","EbsConfiguration":{"EbsBlockDeviceConfigs":[{"VolumeSpecification":{"SizeInGB":32,"VolumeType":"gp2"},"VolumesPerInstance":1}]},"InstanceGroupType":"MASTER","InstanceType":"m4.large","Name":"Master - 1"}]' \
	--auto-terminate \
	--auto-scaling-role EMR_AutoScaling_DefaultRole \
	--ebs-root-volume-size 10 \
	--service-role EMR_DefaultRole \
	--enable-debugging \
	--name 'Sqoop' \
	--scale-down-behavior TERMINATE_AT_TASK_COMPLETION \
	--region ap-southeast-2
