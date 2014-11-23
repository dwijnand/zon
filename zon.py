#!/usr/bin/env python

from multiprocessing import Pool
import boto.ec2


def main():
    regions = ['eu-west-1', 'us-east-1', 'us-west-2']
    pool = Pool(len(regions))
    num_reg_instances = pool.map(ec2_count, regions)
    print 'Total instances: {0}'.format(sum(num_reg_instances))


def ec2_count(region):
    conn = boto.ec2.connect_to_region(region)
    instances = conn.get_only_instances()
    return len(instances)


if __name__ == "__main__":
    main()
