#!/usr/bin/env python
# coding: utf-8

import socket
from youtube_transcript_api import YouTubeTranscriptApi
from google import genai
import json
import re

def main():
    host = '127.0.0.1'
    port = 8000
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((host, port))

    # 길이 정보 수신
    # length_str = client_socket.recv(1024).decode('utf-8').strip()  # 줄바꿈 문자 제거
    # length = int(length_str)

    # 데이터 수신
    data = client_socket.recv(1024).decode('utf-8').strip()  # 줄바꿈 문자 제거

    message = getAiRecipe(data)
    message = message.replace('`', '').replace('json', '')
    # 정규식 패턴
    pattern = r'\{[^{}]*"recipeName"[^{}]*\}'
    
    # JSON 데이터에서 패턴 매칭
    message = re.findall(pattern, message)[0]
    
    client_socket.sendall(message.encode('utf-8') + b'\n')  # 데이터 전송

    client_socket.close()

def getAiRecipe(videoId):
    transcript_text = ""
    try:
        transcript = YouTubeTranscriptApi.get_transcript(videoId, languages=['ko', 'en'])
        for snippet in transcript:
            start_time = snippet["start"]
            minutes = int(start_time // 60)
            seconds = int(start_time % 60)
            transcript_text += f"{minutes:02d}:{seconds:02d} - {snippet['text']}\n"
    except Exception as e:
        print(f"Error: {e}")
        exit()

    # 타임라인과 함께 자막 출력
    
    api_key = 'AIzaSyCM0CZiMA0aJu97272K70QY_2jDUY8jNQ0'
    client = genai.Client(api_key=api_key)
    orderData = """{"aiRecipeBoolean" : boolean
                    "recipeName": 요리 이름,
                    "recipeIngredient": 재료1|재료2|...재료n,
                    "recipeSeasoning":양념1|양념2|...양념n,
                    "recipeMealSzie":1인분 이라면 1.0,
                    "recipeManual":1.순서1|2.순서2|...n.순서n,
                    "recipeManualTimeStamp": recipeManual순서1에 해당하는 timeStamp 1|recipeManual순서2에 해당하는 timeStamp 2|... recipeManual순서n에 해당하는 timeStamp n,
                    "eatTime": 해당 태그,
                    "ingredients": [해당 태그, 해당 태그,...해당 태그],
                    "cookingStyle": 해당 태그,
                    "cookingMethods": [해당 태그, 해당 태그,...해당 태그]}"""
    prompt = f"""{transcript_text} 위 내용을 요약해서 레시피로 만들어 주는데 {orderData} 형식으로 만들어 해당 내용을 활용해 음식을 만들 수 있는 레시피 인지 아닌지 판단해서 aiRecipeBoolean에 true 혹은 false로 저장해
    recipeIngredient,recipeSeasoning,recipeManual,recipeManualTimeStamp 이 4개는 구분자를 |로 쓰니까 잘 적용 해줘
    recipeManual은 최대한 자세히 적어
    재료랑 양념를 구분해주는데 요리가 여러개가 되면 새로운 리스트로 만들어, 양념에 들어가는 것들은 재료로 구분히지 말고 앙념으로 구분해 그리고 재료와 양념의 양도 표시해
    그리고 이 레시피가 해당되는 태그를 enum은 무조건 한개만 JSON은 1~n개 선택해줘 각 태그에 있는 항목들은 자기 자신 태그에만 넣어 그리고 태그에 넣을 게 없을 때만 기타를 선택해
    eatTime enum('아침','점심','저녁','야식','간식'), 
    ingredients JSON("육류","해산물","채소","곡류","소고기","돼지고기","양고기","닭고기","생선","새우","게","조개","오징어","유제품","기타"), 
    cookingStyle enum("양식", "한식", "중식", "일식", 아시안식, "기타"), 
    cookingMethods JSON('튀김','찜','탕','구이','볶음','조림','샐러드','기타')"""
    aiResponse = client.models.generate_content(
        model="gemini-2.0-flash-001",
        contents=prompt
    )
    return aiResponse.text
if __name__ == "__main__":
    main()

