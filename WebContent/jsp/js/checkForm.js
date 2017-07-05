/** 
 *  form과 관련한 함수 집합
 */

	// 빈 칸 체크
	function isNull(obj, msg){
		
		if(obj.value==""){
			alert(msg);
			obj.focus();
			return true;
		}
		
		return false;
	}
	
	
	// 확장자 체크
	function checkExt(obj){
		var forbidName = ['exe', 'java', 'jsp', 'js', 'class', 'css'];
		var fileName = obj.value;
		
		console.log(fileName);
		
		var ext = fileName.substring(fileName.lastIndexOf('.')+1);
		
		for(var i=0; i<forbidName.length; i++){
			if(forbidName[i]==ext){
				console.log(ext);
				return true;
			}
		}
		
		/*if(forbidName.contain(fileName.substring(fileName.lastIndexOf(".")+1))){
			return true;
		}
		*/
		return false;
		
	}
