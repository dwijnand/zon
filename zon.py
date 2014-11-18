#!/usr/bin/env python

import boto.ec2

def main():
  sizes = [ec2_count(region) for region in ['eu-west-1', 'us-east-1', 'us-west-2']]
  print 'Total instances: ' + str(sum(sizes))

def ec2_count(region):
  conn = boto.ec2.connect_to_region(region)
  reservations = conn.get_all_reservations()
  instances = [inst for res in reservations for inst in res.instances]
  return len(instances)

if __name__ == "__main__":
  main()
