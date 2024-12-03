# ollama-translate
Huggingface에서 배포되고 있는 llama3.2-3B 의 양자화 모델을 이용하여 간단한 번역 기능을 구현

# Framework 버전

| 이름 | 버전 |
| :---------- | :------- |
| Spring boot | v3.4.0 |
| Spring AI   | v1.0.0-M4  |

# 모델 준비
- 모델은 Huggingface에서 배포되고 있는 llama3.2-3B 의 양자화 모델을 이용
- 한국어 사전학습이 완료된 모델이므로 번역에 이용할 수 있다
- [Bllossom-3B 양자화 모델](https://huggingface.co/Bllossom/llama-3.2-Korean-Bllossom-3B-gguf-Q4_K_M)
- 모델의 실행은 로컬에서 수행하며, [Ollama](https://ollama.com/) 를 이용하였다 

# 실행 방법
- `http://localhost:8080/` 에서 실행한다

![image](https://github.com/user-attachments/assets/fe7e16a8-6238-48a0-bcf3-a2efd7e1697d)

  
