#!/bin/bash

set -e

REDSHIFTHOST=$1
USERNAME=$2
PASSWORD=$3
TABLE=$4
TARGET_S3=$5

sqoop import --connect jdbc:redshift://${REDSHIFTHOST} --driver com.amazon.redshift.jdbc41.Driver --username ${USERNAME} --password ${PASSWORD} --table ${TABLE} --target-dir ${TARGET_S3} -m 1 --fields-terminated-by '\t' --lines-terminated-by '\n' --optionally-enclosed-by '\"'
