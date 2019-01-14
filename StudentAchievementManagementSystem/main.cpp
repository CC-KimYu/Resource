  #include <iostream>
#include <string>
#include <stdlib.h>
#include <fstream>
#include <vector>
#include <sstream>
using namespace std;

//����ѧ������һ��ѧ������ѧ������ 
class Student{
	public:
		/*���캯��*/
		Student(string studentID,string name,int grade){
			//��ʼ�� 
			this->studentID=studentID;
			this->name=name;
			this->grade=grade;
		}
		/*��������*/
		~Student(){
		}                                         
		void setStudentID(string studentID){
			this->studentID=studentID;
		}
		void setName(string name){
			this->name=name;
		}
		void setGrade(int grade){
			this->grade=grade;
		}
		string getStudentID(){
			return this->studentID;
		}
		string getName(){
			return this->name;
		}
		int getGrade(){
			return this->grade;
		}
		string toString(){
			string str="{";
			str.append(this->studentID);
			str.append(",");
			str.append(this->name);
			str.append(",");
			string temp;
			stringstream ss;
			ss<<this->grade;
			ss>>temp;//intתstring 
			str.append(temp); 
			str.append("}");
			return str;
		}
	private:
		string studentID; //ѧ�� 
		string name;//���� 
		int grade;//�ɼ� 
}; 
typedef Student* DataType; /*DataType��������Ԫ������*/

//�Զ���һ���������������ѧ������Ϣ����Ϊʹ�ò��롢ɾ���϶�����ѡ��������������Ա� 
typedef struct node{
	DataType data; /*student�������ѧ������*/
	struct node *next; /*next������̵ĵ�ַ*/ 
}LinkList;

//�ÿձ�
void InitList(LinkList *head){
	head=(LinkList *)malloc(sizeof(LinkList)); //����ͷ��� 
	head->next=NULL;
} 
//���ĳ���
int Length(LinkList *head){
	int length=0;//�������ĳ���
	LinkList *p;
	p=head->next;
	cout<<p<<endl;
	while(p!=NULL){
		length++;
		p=p->next;
	} 
	return length;
} 
//ȡ���
LinkList* Get(LinkList *head,int i){
	LinkList *p=head;//��ͷ��ʼɨ��  ָ���׽�� 
	int j=0;
	while(p->next!=NULL&&j<i){ //δ����β��ͬʱû���ߵ���i����� 
		p=p->next;
		j++;
	}
	if(i==j){
		return p;//�ҵ��ý�� 
	}else{
		return NULL;//δ�ҵ��ý�� 
	}
} 
//β�巨����
LinkList* CreateRear(LinkList *head,DataType x){
	LinkList *s,*r;
	r=head;
	s=(LinkList*)malloc(sizeof(LinkList));//�����½��
	s->data=x;
	if(head->next==NULL){
		head->next=s;
	}else{
		r->next=s;
	}
	if(r!=head)
		r->next=NULL;
	return head;
} 
//���� 
int Insert(LinkList *head,int i,DataType x){
	LinkList *p,*s;
	p=Get(head,i-1);
	cout<<p<<endl;
	if(p==NULL){
		cout<<"error\n"<<endl;
		return 0; //����ʧ�� 
	}
	s=(LinkList *)malloc(sizeof(LinkList));
	s->data=x;
	s->next=p->next;
	p->next=s;
	return 1;//�����Ա 
}
//������ӡ����
void Print(LinkList *head){
	LinkList *p=head->next;//ָ���׽�� 
	while(p!=NULL){
		cout<<p->data->toString()<<endl;
		p=p->next; 
	}
}  
/* run this program using the console pauser or add your own getch, system("pause") or input loop */
int main(int argc, char** argv) {
	ifstream in("StudentData.txt");//��ȡ�ı��ļ� 
	string message;
	LinkList *head;
	//InitList(head);//��ʼ��ͷָ�� 
	if(head!=NULL){
		cout<<"��ʼ��ͷָ��ɹ�"<<endl;
	}
//	cout<<head<<endl;
//	cout<<head->data<<head->next;
	while(getline(in,message)){
		string str=message.c_str();
		string result;
    	vector<string> results;//���ڴ�ŷָ����ַ��� 
		stringstream input(str);//���ַ�������input�� 
		 //���������result�У�������results�� 
   		 while(input>>result){
   		 	results.push_back(result);
   		 }
   		
   		string studentID=results[0]; //ѧ�� 
		string name=results[1];//���� 
		int grade=atoi(results[2].c_str());//�ɼ� 
		
		Student* student=new Student(studentID,name,grade);
		//cout<<student->toString()<<endl;
		//head=CreateRear(head,student);
		delete student;//���� 
	}
	cout<<"��ĳ���Ϊ��"<<Length(head)<<endl;
	//Print(head);
	return 0;
}
