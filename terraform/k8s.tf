resource "kubernetes_deployment" "name" {
  metadata {
    name = "your-spring-app"
  }
  spec {
    replicas = 1
    hostNetwork: true
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
        container {
          name  = "scrapercontainer"
          image = var.container_image
          port {
            container_port = 80
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