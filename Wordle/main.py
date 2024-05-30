from pydoc import stripid
import random


def guess_progress (theanswer,theguess):
    position = 0
    clue = ""
    for letter in theguess:
        if letter == theanswer[position]:
            clue += 'G'
        elif letter in theanswer:
            clue += 'O'
        else:
            clue += 'X'
        position += 1
    print(clue)
    return clue == "GGGGG"

word_list = []
word_file = open("words.txt")

for word in word_file:
    word_list.append(word.strip())

today_wordle = random.choice(word_list)

num_of_guesses = 0
guessed_correctly = False

while num_of_guesses < 6 and not guessed_correctly:
    guess = str(input("enter your 5 letter word: "))

    guess_length = len(guess)

    while guess_length != 5:
        guess = str(input("invalid guess. input a guess with 5 letters: "))
        guess_length = len(guess)




    print("you have guessed: ", guess)

    num_of_guesses = num_of_guesses + 1

    guessed_correctly = guess_progress(today_wordle,guess)


if guessed_correctly:
    print("Congratulations, you guessed the correct word in ", num_of_guesses)
else:
    print("You have used up all of your guesses, the correct word is ", today_wordle)
