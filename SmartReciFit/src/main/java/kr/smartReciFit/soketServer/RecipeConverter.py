#!/usr/bin/env python
# coding: utf-8

import socket
from google import genai
import json

def main():
    host = '127.0.0.1'
    port = 8000
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((host, port))

    # 데이터 수신
    data = client_socket.recv(1024).decode('utf-8').strip()  # 줄바꿈 문자 제거
    
    message = getRecipeConverter(data)
    message = message.replace('`', '').replace('json', '')
    # 정규식 패턴
    pattern = r'\{[^{}]*"recipeName"[^{}]*\}'
    
    # JSON 데이터에서 패턴 매칭
    message = re.findall(pattern, message)[0]
    
    client_socket.sendall(message.encode('utf-8') + b'\n')  # 데이터 전송

    client_socket.close()

def getRecipeConverter(recipe):
    
    api_key = 'AIzaSyCM0CZiMA0aJu97272K70QY_2jDUY8jNQ0'
    client = genai.Client(api_key=api_key)
    orderData = """{"recipeIngredient": 재료1|재료2|...재료n,
                    "recipeSeasoning":양념1|양념2|...양념n,}"""
    prompt = f"""{recipe} 위 내용은 음식의 레시피 인데 mealSize인분의 음식이야 이 음식의 mealSize 를 0.8로 해서 
                    ingredients와 seasonings을 수정해가지고 {orderData} 형식으로 만들어"""
    aiResponse = client.models.generate_content(
        model="gemini-2.0-flash-001",
        contents=prompt
    )
    return aiResponse.text
if __name__ == "__main__":
    main()