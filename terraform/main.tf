data "google_container_engine_versions" "default" {
  location = "europe-west3"
}

data "google_client_config" "current" {}

resource "google_container_cluster" "default" {
  name               = "my-first-cluster"
  location           = "europe-west3"
  initial_node_count = 6
  min_master_version = data.google_container_engine_versions.default.latest_master_version

  node_config {
    machine_type = "g1-small"
    disk_size_gb = 64
  }

  provisioner "local-exec" {
    when    = destroy
    command = "sleep 90"
  }
}

