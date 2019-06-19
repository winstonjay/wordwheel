import random
from collections import namedtuple
from collections import defaultdict

Wordwheel = namedtuple('Wordwheel', ['centre', 'slices'])

def findwords(words, wordwheel):

    found = set()
    for word in words:
        for w in word:
            
    return

def buildwheel(n=8):
    'for a given n of slices return a new word wheel'
    centre = randchar()
    slices = defaultdict(int)
    for _ in range(n):
        slices[randchar()] += 1
    slices[centre] += 1
    return Wordwheel(centre=centre, slices=slices)

def randchar():
    'return a random letter from the alphabet'
    return random.choice(alphabet)

alphabet = 'abcdefghijklmnopqrstuvwxyz'
