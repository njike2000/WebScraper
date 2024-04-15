
resource "kubernetes_deployment" "name" {
  metadata {
    name = "your-spring-app"
  }
  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "your-spring-app"
      }
    }
    template {
      metadata {
        labels = {
          app = "your-spring-app"
        }
      }
      spec {
        host_network = true
        container {
          name  = "scrapercontainer"
          image = "us.gcr.io/${GCP_PROJECT_ID}/scraperappimage:${IMAGE_TAG}"
          port {
            container_port = 8080
          }
        }
      }
    }
  }
}


# resource "google_compute_address" "default" {
# name   = "ipforservice"
  #region = var.region
#}
#resource "kubernetes_service" "appservice" {
#  metadata {
#    name = "scraperapp-lb-service"
#  }
#  spec {
 #   type             = "LoadBalancer"
 #   load_balancer_ip = google_compute_address.default.address
#    port {
 #     port        = 8080
 #     target_port = 8080
#    }

#  }
#}