<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>

    new daum.Postcode({

        oncomplete: function(data) {

            if(data.userSelectedType=="R"){

                // userSelectedType : �˻� ������� ����ڰ� ������ �ּ��� Ÿ��

                // return type : R - roadAddress, J : jibunAddress

                // TestApp �� �ȵ���̵忡�� ����� �̸�

                window.TestApp.setAddress(data.zonecode, data.roadAddress, data.buildingName);

            }

            else{

                window.TestApp.setAddress(data.zonecode, data.jibunAddress, data.buildingName);

            }       

        }

    }).open();

</script>

</body>
</html>