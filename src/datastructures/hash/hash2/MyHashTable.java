package datastructures.hash.hash2;
/**
 *	演示采用再哈希法解决冲突的Hash表
 */
public class MyHashTable {
	private DataItem[] hashArray;
	private int arraySize;
	
	public MyHashTable(int size){
		this.arraySize = size;
		this.hashArray = new DataItem[this.arraySize];
	}
	/**
	 * 第一次的hash函数
	 * @param key
	 * @return
	 */
	public int hashFun(int key){
		return key%this.arraySize;
	}
	/**
	 * 再哈希函数
	 * @param key
	 * @return
	 */
	public int hashFun2(int key){
		return 7-key%7;
	}
	
	/**
	 * 新增数据对象
	 * @param item
	 */
	public void insert(DataItem item){
		int hashVal = this.hashFun(item.getKey());
		int stepSize = this.hashFun2(item.getKey());
		
		//检查是否冲突，如果冲突了就向下查找第一个未使用的位置
		while(this.hashArray[hashVal]!=null){
			hashVal += stepSize;
			//检查是否到底，到底了从头查
			hashVal %= this.arraySize;
		}
		
		//放入数据
		this.hashArray[hashVal] = item;		
	}
	/**
	 * 删除数据对象
	 * @param key
	 */
	public void remove(int key){
		int hashVal = this.hashFun(key);
		int stepSize = this.hashFun2(key);
		
		while(this.hashArray[hashVal]!=null){
			if(this.hashArray[hashVal].getKey() == key){
				this.hashArray[hashVal] = null;
				break;
			}
			
			hashVal += stepSize;
			//检查是否到底，到底了从头查
			hashVal %= this.arraySize;
		}
	}
	/**
	 * 根据key获取数据对象
	 * @param key
	 * @return
	 */
	public DataItem findData(int key){
		int hashVal = this.hashFun(key);
		int stepSize = this.hashFun2(key);
		
		while(this.hashArray[hashVal]!=null){
			if(this.hashArray[hashVal].getKey() == key){
				return this.hashArray[hashVal];
			}
			
			hashVal += stepSize;
			//检查是否到底，到底了从头查
			hashVal %= this.arraySize;
		}
		return null;
	}
	
	public void displayHashTable(){
		for(DataItem d : this.hashArray){
			System.out.print(d+" , ");
		}
		System.out.println("=======================2222222");
	}
	
	public static void main(String[] args) {
		MyHashTable t = new MyHashTable(10);
		
		for(int i=1;i<=10;i++){
			DataItem d = new DataItem(i,i+100);
			t.insert(d);
		}
		
		t.displayHashTable();
		
		t.remove(3);
		
		t.displayHashTable();
	}
}
