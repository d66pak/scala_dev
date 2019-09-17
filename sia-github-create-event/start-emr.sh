#!/usr/bin/env bash

bash copyJARS3.sh

aws emr create-cluster \
--auto-scaling-role EMR_AutoScaling_DefaultRole \
--applications Name=Hadoop Name=Spark \
--ebs-root-volume-size 10 \
--ec2-attributes '{"KeyName":"Development","InstanceProfile":"EMR_EC2_DefaultRole","SubnetId":"subnet-0559d84495d964dcd","EmrManagedSlaveSecurityGroup":"sg-a3c564db","EmrManagedMasterSecurityGroup":"sg-84c362fc"}' \
--service-role EMR_DefaultRole \
--enable-debugging \
--release-label emr-5.20.0 \
--log-uri 's3n://aws-logs-167856709162-ap-southeast-2/elasticmapreduce/' \
--steps '[{"Args":["spark-submit","--deploy-mode","cluster","--class","GitHubCreateEvent","s3://murali.test/siagithubcreateevent_2.11-0.1.jar","s3://murali.test/github-archive/","s3://murali.test/github-create-event-3/","s3://murali.test/gitHubEmployees.txt"],"Type":"CUSTOM_JAR","ActionOnFailure":"CONTINUE","Jar":"command-runner.jar","Properties":"","Name":"GitHub create event"}]' \
--name 'SpartTestEMR' \
--instance-groups '[{"InstanceCount":1,"BidPrice":"0.060","InstanceGroupType":"MASTER","InstanceType":"m2.2xlarge","Name":"Master - 1"},{"InstanceCount":2,"BidPrice":"0.036","EbsConfiguration":{"EbsBlockDeviceConfigs":[{"VolumeSpecification":{"SizeInGB":32,"VolumeType":"gp2"},"VolumesPerInstance":1}]},"InstanceGroupType":"CORE","InstanceType":"m4.large","Name":"Core - 2"}]' \
--scale-down-behavior TERMINATE_AT_TASK_COMPLETION \
--region ap-southeast-2 \
--auto-terminate