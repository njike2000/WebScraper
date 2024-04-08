resource "kubernetes_deployment" "name" {
  metadata {
    name = "your-spring-app"

  }
  spec {
    replicas = 1
    selector {

    }
    template {
      metadata {
        name = "your-spring-app"

      }
      spec {
        container {
          name  = "scrapercontainer"
          image = var.nginx:1.21.6
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
resource "kubernetes_service" "appservice" {
  metadata {
    name = "scraperapp-lb-service"
  }
  spec {
    type             = "LoadBalancer"
    load_balancer_ip = google_compute_address.default.address
    port {
      port        = 8080
      target_port = 8080
    }

  }
}