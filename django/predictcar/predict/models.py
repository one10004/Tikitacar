from django.db import models

class Car(models.Model):
    year = models.CharField(max_legnth=10)
    cc = models.CharField(max_legnth=10)
    distance = models.CharField(max_legnth=10)
    color = models.CharField(max_legnth=10)
    gear = models.CharField(max_legnth=10)
    fuel = models.CharField(max_legnth=10)
    option = models.CharField(max_legnth=10)
    seat = models.CharField(max_legnth=10)
    flooding = models.CharField(max_legnth=10)
    insurance = models.CharField(max_legnth=10)
    release = models.CharField(max_legnth=10)

    def __str__self():
        return self.title
