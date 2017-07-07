namespace java tutorial

struct AnswerStruct
{
	1: string answerObject
} 

service AnswerService
{
        AnswerStruct getAnswer(1:string answer),
}