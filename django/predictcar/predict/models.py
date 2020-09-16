from django.db import models

class Car(models.Model):
    year = models.IntegerField()
    cc = models.IntegerField()
    distance = models.IntegerField()
    color = models.CharField(max_legnth=10)
    gear = models.harField(max_length=10)
    fuel = models.CharField(max_length=10)
    option = models.IntegerField()
    seat = models.IntegerField()
    flooding = models.IntegerField()
    insurance = models.IntegerField()
    release = models.IntegerField()

    def __str__self():
        return self.title
