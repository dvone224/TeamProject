#!/usr/bin/env python
# coding: utf-8

import socket
from google import genai
import json
import re

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
    pattern = r'\{[^{}]*"recipeIngredient"[^{}]*\}'
    
    # JSON 데이터에서 패턴 매칭
    message = re.findall(pattern, message)[0]
    
    client_socket.sendall(message.encode('utf-8') + b'\n')  # 데이터 전송

    client_socket.close()

def getRecipeConverter(recipe):
    
    api_key = 'AIzaSyCM0CZiMA0aJu97272K70QY_2jDUY8jNQ0'
    client = genai.Client(api_key=api_key)
    orderData = """{"recipeIngredient": 재료1|재료2|...재료n,
                    "recipeSeasoning":양념1|양념2|...양념n,}"""
                    
    prompt = f"""{recipe}이 레시피를 'targetMealSize'인분에 맞게 조절해주세요.

            **가장 중요한 것은, 재료와 양념의 양을 조절할 때 단순히 'targetMealSize / mealSize' 비율로 곱하는 선형적인 계산을 넘어서야 한다는 점입니다.** 전문적인 요리 지식을 적용하여 다음과 같은 요소들을 고려한 미묘하고 세밀한 조정을 수행해야 합니다:

            1.  **수분 증발**: 물, 육수, 소스 등 액체류는 조리 시간, 냄비 크기, 화력 등에 따라 증발량이 달라지므로, 인분 수가 늘어날 때 단순 비례보다 적게 늘어날 수 있습니다. 특히 뚜껑을 열고 오래 끓이는 찌개나 스튜는 더욱 그렇습니다.
            2.  **향신료/강한 양념**: 마늘, 생강, 고추, 허브, 카레 가루 등 향이 강한 재료는 양이 늘어날 때 맛과 향이 과도하게 강해질 수 있으므로, 선형적인 비율보다 적게 조절하는 것이 좋습니다.
            3.  **염도/당도**: 소금, 간장, 설탕 등 기본 간은 사람의 미각이 선형적으로 반응하지 않으므로, 단순 비례보다 약간 적게 넣고 조리 과정에서 맛을 보며 조절하는 것이 안전합니다. 김치 자체의 염도도 고려해야 합니다.
            4.  **팽창제/농후제**: 베이킹 파우더, 베이킹 소다, 밀가루, 전분 등은 부피나 농도에 영향을 미치므로, 레시피의 특성과 원하는 결과에 따라 조절 비율이 달라질 수 있습니다. (이 레시피에는 해당되지 않을 수 있지만 일반적인 원칙입니다.)
            5.  **주재료와 부재료의 비율**: 인분 수가 크게 변할 때, 주재료(예: 고기)와 부재료(예: 채소)의 비율을 미세하게 조정하여 전체적인 맛의 균형을 유지해야 할 수 있습니다.
            6.  **조리법의 특성**: 볶음 요리의 기름 양, 튀김 요리의 기름 양 등은 인분 수에 정비례하지 않을 수 있습니다.

            **요구사항:**

            *   위의 고려사항들을 종합적으로 판단하여 각 재료와 양념의 양을 **가장 현실적이고 맛의 밸런스를 유지하는 방향으로** 조절해주세요.
            *   필요하다면 소수점(예: 1.5 큰술, 0.75 개)을 사용하여 양을 정확하게 계산해주세요.
            *   단위는 원래 레시피의 단위를 유지하는 것을 기본으로 하되, 더 이해하기 쉬운 표준 단위(예: g, ml)로 변환할 수 있다면 변환해도 좋습니다.
            *   조절된 레시피의 재료와 양념을 **반드시** {orderData} 형식의 JSON 객체로 출력해주세요. 각 키('recipeIngredient', 'recipeSeasoning')의 값은 각 항목(이름 양 단위)을 포함하고 파이프(|)로 구분된 **하나의 문자열**이어야 합니다."""

    # prompt = f"""{recipe} 위 내용은 음식의 레시피 인데 mealSize인분의 음식이야 이 음식의 mealSize 를 targetMealSize의 값으로 해서 {orderData} 형식으로 만드는데
    #                ingredients와 seasonings을 수정할 때 mealSize 조절은 어떤 조리법, 재료, 양념, 증발양 등 여러 변수에 따라서 조절해야 하는 다르잖아 이런 변수들을 적용해서 세밀하게 수정해줘 소수점이 필요하다면 사용해도 좋아"""
    aiResponse = client.models.generate_content(
        model="gemini-2.0-flash-001",
        contents=prompt
    )
    return aiResponse.text
if __name__ == "__main__":
    main()