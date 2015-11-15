from django.shortcuts import render
from django.http import HttpResponse
from django.shortcuts import render_to_response, RequestContext
from helloworld.models import Query
from django.http import HttpResponseRedirect

# Class

class Word():
	def __init__(self, word, parsed_list):
		self.word = word
		self.parsed_list = parsed_list
	def get_word(self):
		return word
	def get_parsed_list(self):
		return parsed_list


# Create your views here.

def helloworld(request):
	return render_to_response('base.html', locals(), context_instance = RequestContext(request))

def home(request):	
	return render(request, 'dkhome.html', {"m_query":Query.objects.filter(part="문화유람 "), "h_query":Query.objects.filter(part="학술연구 "), "selected_part":"문화/유람 "})

def about(request):
	return render(request, "about.html", {"selected_part":"문화/유람 "})

def handle_query(request, _part, _value):
	if(_part == "my"):
		selected_list = Query.objects.filter(part="문화유람 ", value=_value)
		if(len(selected_list) != 0):
			selected_list[0].delete()
		s_part = "문화/유람 "
	elif(_part == "hy"):
		selected_list = Query.objects.filter(part="학술연구 ", value=_value)
		if(len(selected_list) != 0):
			selected_list[0].delete()
		s_part = "학술/연구 "
	else:
		selected_list = Query.objects.filter(part=_part, value=_value)
		if(len(selected_list) == 0):
			new_Query = Query(part=_part, value=_value)
			new_Query.save()
			s_part = _part[:2] + "/" + _part[2:]
	return render(request, 'dkhome.html', {"m_query":Query.objects.filter(part="문화유람 "), "h_query":Query.objects.filter(part="학술연구 "), "selected_part":s_part})

def make_dq(request):
	mList = dict([(w.value[0], w.value) for w in Query.objects.filter(part="문화유람 ")])
	hList = dict([(w.value[0], w.value) for w in Query.objects.filter(part="학술연구 ")])
	dq_list = []
	for line in open("dict/CombineFile.txt", "r"):
		mFlag = False
		hFlag = False
		count = 0
		parse_list = []
		for ch in line.strip():
			if ch in mList.keys() and mList[ch] not in parse_list:
				mFlag = True
				count += 1
				parse_list.append(mList[ch])
			elif ch in hList.keys() and hList[ch] not in parse_list:
				hFlag = True
				count += 1
				parse_list.append(hList[ch])
			else:
				parse_list.append("JokerWord")
		if (mFlag and hFlag and count >= 3):
			dq_list.append(Word(line, parse_list))
	return render(request, "make.html", {"selected_part":"문화/유람 ", "dq_list":dq_list})


def clear(request):
	Query.objects.all().delete()
	return render(request, 'dkhome.html', {"selected_part":"문화/유람 "})

