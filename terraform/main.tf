# Define data sources to fetch required information
data "google_container_engine_versions" "default" {
  location = "europe-west3"
}

data "google_client_config" "current" {}

# Define the GKE cluster resource
resource "google_container_cluster" "default" {
  name               = "my-first-cluster"
  location           = "europe-west1-b"
  initial_node_count = 3
  min_master_version = data.google_container_engine_versions.default.latest_master_version

  # Set deletion_protection to false
  deletion_protection = false

  node_config {
    machine_type = "g1-small"
    disk_size_gb = 32
  }

  provisioner "local-exec" {
    when    = destroy
    command = "sleep 90"
  }
}

# Define the workflow for deploying the infrastructure
# This could include steps such as authentication, Docker image build, Terraform initialization, plan, and apply.


# Define the workflow for deploying the infrastructure
# This could include steps such as authentication, Docker image build, Terraform initialization, plan, and apply.

