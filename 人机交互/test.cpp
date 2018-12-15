

#include <sapi.h>
#include <sphelper.h>
	void main(int argc,char* argv[]){
		ISpVoice * pVoice=NULL;
		ISpObjectToken * pSpToken=NULL;
		if (FAILED(::CoInitialize(NULL)))
		{
			printf("Error to intiliaze COM");
			return;
		}//��ʼ��COM
		HRESULT hr=CoCreateInstance(CLSID_SpVoice,NULL,CLSCTX_ALL,IID_ISpVoice,(void **)&pVoice);
		if(SUCCEEDED(hr))
		{
			hr=pVoice->SetVolume(80);
			hr=pVoice->SetRate(0);
			hr=pVoice->Speak(L"�������",SPF_DEFAULT,NULL);//����������

			if (SUCCEEDED(SpFindBestToken(SPCAT_VOICES,L"language=804",NULL,&pSpToken)))
			{
				pVoice->SetVoice(pSpToken);
				pVoice->Speak(L"�������",SPF_DEFAULT,NULL);
				pSpToken->Release();
			}
			pVoice->Release();
			pVoice=NULL;
		}
		::CoUninitialize();
	}
