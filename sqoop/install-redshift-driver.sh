#!/bin/bash

set -e
wget -S -T 10 -t 5 https://s3.amazonaws.com/redshift-downloads/drivers/jdbc/1.2.32.1056/RedshiftJDBC41-no-awssdk-1.2.32.1056.jar
sudo mv RedshiftJDBC41-no-awssdk-1.2.32.1056.jar /usr/lib/sqoop/lib/

