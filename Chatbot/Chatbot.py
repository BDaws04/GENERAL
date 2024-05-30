import openai
import os 

key = os.getenv('OPEN_AI_KEY', None)

messages = [ {"role": "system", "content":  
              "You are a intelligent assistant."} ] 

def queryGPT(message):

    if message: 
        messages.append( 
            {"role": "user", "content": message}, 
        ) 
        chat = openai.ChatCompletion.create( 
            model="gpt-3.5-turbo", messages=messages 
        ) 
    reply = chat.choices[0].message.content 
    print(f"ChatGPT: {reply}") 
    messages.append({"role": "assistant", "content": reply}) 
    
if key is not None:
    openai.api_key = key

    message = input("User: ")

    while message != 'Exit':
        queryGPT(message)
        message = input("User: ")

    
else:
    print("Insert API-Key into enviornment variables, then try again. ")
    exit(0)

