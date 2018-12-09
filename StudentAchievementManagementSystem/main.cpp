  #include <iostream>
#include <string>
#include <stdlib.h>
#include <fstream>
#include <vector>
#include <sstream>
using namespace std;

//根据学生抽象一个学生类存放学生数据 
class Student{
	public:
		/*构造函数*/
		Student(string studentID,string name,int grade){
			//初始化 
			this->studentID=studentID;
			this->name=name;
			this->grade=grade;
		}
		/*析构函数*/
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
			ss>>temp;//int转string 
			str.append(temp); 
			str.append("}");
			return str;
		}
	private:
		string studentID; //学号 
		string name;//姓名 
		int grade;//成绩 
}; 
typedef Student* DataType; /*DataType代表数据元素类型*/

//自定义一个链表来存放所有学生的信息（因为使用插入、删除较多所以选择链表而不是线性表） 
typedef struct node{
	DataType data; /*student用来存放学生对象*/
	struct node *next; /*next存放其后继的地址*/ 
}LinkList;

//置空表
void InitList(LinkList *head){
	head=(LinkList *)malloc(sizeof(LinkList)); //建立头结点 
	head->next=NULL;
} 
//求表的长度
int Length(LinkList *head){
	int length=0;//存放链表的长度
	LinkList *p;
	p=head->next;
	cout<<p<<endl;
	while(p!=NULL){
		length++;
		p=p->next;
	} 
	return length;
} 
//取结点
LinkList* Get(LinkList *head,int i){
	LinkList *p=head;//从头开始扫描  指向首结点 
	int j=0;
	while(p->next!=NULL&&j<i){ //未到表尾，同时没有走到第i个结点 
		p=p->next;
		j++;
	}
	if(i==j){
		return p;//找到该结点 
	}else{
		return NULL;//未找到该结点 
	}
} 
//尾插法建表
LinkList* CreateRear(LinkList *head,DataType x){
	LinkList *s,*r;
	r=head;
	s=(LinkList*)malloc(sizeof(LinkList));//申请新结点
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
//插入 
int Insert(LinkList *head,int i,DataType x){
	LinkList *p,*s;
	p=Get(head,i-1);
	cout<<p<<endl;
	if(p==NULL){
		cout<<"error\n"<<endl;
		return 0; //插入失败 
	}
	s=(LinkList *)malloc(sizeof(LinkList));
	s->data=x;
	s->next=p->next;
	p->next=s;
	return 1;//插入成员 
}
//遍历打印链表
void Print(LinkList *head){
	LinkList *p=head->next;//指向首结点 
	while(p!=NULL){
		cout<<p->data->toString()<<endl;
		p=p->next; 
	}
}  
/* run this program using the console pauser or add your own getch, system("pause") or input loop */
int main(int argc, char** argv) {
	ifstream in("StudentData.txt");//读取文本文件 
	string message;
	LinkList *head;
	//InitList(head);//初始化头指针 
	if(head!=NULL){
		cout<<"初始化头指针成功"<<endl;
	}
//	cout<<head<<endl;
//	cout<<head->data<<head->next;
	while(getline(in,message)){
		string str=message.c_str();
		string result;
    	vector<string> results;//用于存放分割后的字符串 
		stringstream input(str);//将字符串读到input中 
		 //依次输出到result中，并存入results中 
   		 while(input>>result){
   		 	results.push_back(result);
   		 }
   		
   		string studentID=results[0]; //学号 
		string name=results[1];//姓名 
		int grade=atoi(results[2].c_str());//成绩 
		
		Student* student=new Student(studentID,name,grade);
		//cout<<student->toString()<<endl;
		//head=CreateRear(head,student);
		delete student;//销毁 
	}
	cout<<"表的长度为："<<Length(head)<<endl;
	//Print(head);
	return 0;
}
