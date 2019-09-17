#!/usr/bin/env bash

aws ec2 describe-spot-price-history \
--start-time=$(date +%s) \
--product-descriptions="Linux/UNIX" \
--query 'SpotPriceHistory[*].{az:AvailabilityZone, price:SpotPrice}' \
--instance-types m4.large

aws ec2 describe-spot-price-history \
--start-time=$(date +%s) \
--product-descriptions="Linux/UNIX" \
--query 'SpotPriceHistory[*].{az:AvailabilityZone, price:SpotPrice}' \
--instance-types m2.2xlarge
