from django.contrib import admin
from helloworld.models import Query

# Register your models here.

class QueryAdmin(admin.ModelAdmin):
	list_display = ('part', 'value')

admin.site.register(Query, QueryAdmin)
