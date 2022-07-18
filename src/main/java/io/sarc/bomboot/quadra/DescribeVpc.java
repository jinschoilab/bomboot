package io.sarc.bomboot.quadra;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeVpcsRequest;
import software.amazon.awssdk.services.ec2.model.DescribeVpcsResponse;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.Vpc;

public class DescribeVpc {
	public void get(String vpcId) {
		final String usage = "\n" + "Usage:\n" + "   <vpcId>\n\n" + "Where:\n"
				+ "   vpcId - A  VPC ID that you can obtain from the AWS Management Console (for example, vpc-xxxxxf2f). \n\n";

		Region region = Region.US_EAST_1;
		Ec2Client ec2 = Ec2Client.builder().region(region).credentialsProvider(ProfileCredentialsProvider.create())
				.build();

		describeEC2Vpcs(ec2, vpcId);
		ec2.close();
	}

	public static void describeEC2Vpcs(Ec2Client ec2, String vpcId) {

		try {
			DescribeVpcsRequest request = DescribeVpcsRequest.builder().vpcIds(vpcId).build();

			DescribeVpcsResponse response = ec2.describeVpcs(request);

			for (Vpc vpc : response.vpcs()) {
				System.out.printf("Found VPC with id %s, " + "vpc state %s " + "and tennancy %s", vpc.vpcId(),
						vpc.stateAsString(), vpc.instanceTenancyAsString());
			}
		} catch (Ec2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
		}
	}
}