from django.db import models

# Create your models here.

class Query(models.Model):
	part = models.CharField(max_length=8)
	value = models.CharField(max_length=20)

