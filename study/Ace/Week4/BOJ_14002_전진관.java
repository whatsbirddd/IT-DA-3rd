import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
  static int[] a;
  static int[] d;
  static int[] v;

  // v[i]를 쫓아가면서 출력하면 순서대로 뽑아낼 수 있다
  static String go(int p){
    if( p == -1 ){ // v는 -1로 초기화했으므로 -1이란 얘기는 얘가 부분수열의 처음이란 뜻. 리턴.
      return "";
    }
    // 재귀함수 호출하면 차례대로 출력이 되겠죵?
    return go(v[p]) + " " + a[p];
  }
  public static void main(String []args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    a = new int[n]; // 주어진 배열을 담을 변수
    d = new int[n]; // 해당 인덱스까지 가장 긴 부분수열의 길이를 담을 변수
    v = new int[n]; // 해당 인덱스가 포함된 부분수열에서 해당 값 이전에 있던 요소의 인덱스를 저장할 변수

    for(int i = 0 ; i < n ; i++ ){
      a[i] = Integer.parseInt(st.nextToken());
    }

    int max = 1; // 최대 길이 출력시 사용할 변수 
    int last = 0; // 최대 길이를 만족시키는 부분수열의 마지막 인덱스를 저장할 변수
    for( int i = 0 ; i < n ; i++ ){
      d[i] = 1; // 초기화 여기서 걍 해줘도 된다
      v[i] = -1; // 이것도 -1로 초기화 해줘야한다( 디폴트 0이라서 0 가리키는 것처럼 보이지 않도록)
      for(int j = 0 ; j < i ; j++ ){
        // 가긴증이랑 조건 같다. 내 앞에있는 요소중 나보다 값은 작으면서
        // 부분수열의 길이는 크거나 같으면 스리슬적 숟가락 얹기
        if(a[j] < a[i] && d[j] >= d[i] ){
          d[i] = d[j]+1;
          v[i] = j; // 이것이 포인트다. 숟가락을 얹긴 얹되 구체적으로 걔가 몇번째 애인지를 기록한다
          if( d[i] > max ){
            max = d[i];
            last = i;
          }
        }
      }
    }
    System.out.println(max);
    System.out.println(go(last).trim());
  }
}