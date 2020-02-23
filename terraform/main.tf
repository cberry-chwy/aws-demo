provider "aws" {
  region = "us-east-1"
}

resource "aws_instance" "ubuntu18_04-t2_micro" {
  ami = "ami-0d03e44a2333dea65"
  instance_type = "t2.micro"

  user_data = <<-EOF
              #!/bin/bash
              echo "Hello, World" > index.html
              nohup busybox httpd -f -p 8080 &
              EOF

  tags = {
    Name = "Terraform Test"
  }
}

resource "aws_security_group" "instance" {
  name = "terraform-test-instance"

  ingress {
    from_port = 8080
    protocol = "tcp"
    to_port = 8080
    cidr_blocks = ["0.0.0.0/0"]
  }
}