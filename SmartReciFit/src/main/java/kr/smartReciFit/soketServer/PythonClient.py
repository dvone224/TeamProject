#!/usr/bin/env python
# coding: utf-8

# In[ ]:
\
import socket
from google import genai
from PIL import Image
from youtube_transcript_api import YouTubeTranscriptApi

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

    # 데이터 전송
    client_socket.sendall(message.encode('utf-8') + b'\n')  # 데이터 전송

    client_socket.close()

def getAiRecipe(vodeoId):
    full_text = ''
    try:

        transcription = YouTubeTranscriptApi.get_transcript(vodeoId, languages=['ko', 'en'])
        
        # 각 텍스트를 리스트에 추가
        text_list = [content['text'] for content in transcription]
        
        # 리스트의 텍스트를 하나의 문자열로 연결
        full_text = ' '.join(text_list)
    except Exception as e:
        print(f"Error fetching transcript: {e}")
            # API 키 설정
    api_key = 'AIzaSyCM0CZiMA0aJu97272K70QY_2jDUY8jNQ0'
    
    # 클라이언트 생성
    client = genai.Client(api_key=api_key)
    
    # 텍스트 생성
    prompt = f'{full_text} 레시피 알려줘'
    response = client.models.generate_content(
        model="gemini-2.0-flash-001",
        contents=prompt
    )
    return response.text
if __name__ == "__main__":
    main()

