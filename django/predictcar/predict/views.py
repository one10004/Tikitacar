from django.shortcuts import render
from .models import Car
from .serializers import CarSerializer
from rest_framework import viewsets

class Car_restful_main(viewsets.ModelViewSet):
    queryset = Car.objects.all()
    serializer_class = CarSerializer
# Create your views here.
