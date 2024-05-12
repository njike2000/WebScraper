variable "IMAGE_TAG" {
  default = ""
}

variable "GCP_PROJECT_ID" {
  default = ""
}

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

resource "kubernetes_service" "appservice" {
  metadata {
    name = "scraperapp-lb-service"
  }
  spec {
    type = "LoadBalancer"
    port {
      port        = 80
      target_port = 80
    }
    selector = {
      app = "your-spring-app"
    }
  }
}
