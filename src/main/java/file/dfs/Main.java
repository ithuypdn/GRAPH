/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file.dfs;

import java.util.LinkedList;
import java.util.Stack;
import javax.print.attribute.standard.PrinterStateReason;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author huypd
 */
class Queue<T> {
    private LinkedList<T> data;
    private int count;

    public Queue() {
        data = new LinkedList<>();
        count = 0;
    }
    public void enqueue(T t) {
        data.add(t);
        count++;
    }
    public T dequeue() {
        if(!isEmpty())
            count--;
        return data.remove();
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
class Graph {
    static public int INF = Integer.MAX_VALUE;
    int[][] G;
    int n;
    boolean[] visited;
    
    public Graph() {
        n = 7;
        G = new int[][] {
            {}, //dòng 0, bỏ qua
            {-1, 0, 1, 1, 1, 0, 0, 1},
            {-1, 1, 0, 1, 0, 1, 0, 0},
            {-1, 1, 1, 0, 0, 1, 0, 0},
            {-1, 1, 0, 0, 0, 0, 1, 0},
            {-1, 0, 1, 1, 0, 0, 1, 1},
            {-1, 0, 0, 0, 1, 1, 0, 0},
            {-1, 1, 0, 0, 0, 1, 0, 0}
        };
        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++)
            visited[i] = false;
    }
    void display() {
        System.out.printf("%3c", '\\');
        for (int i = 1; i <= n; i++) 
            System.out.printf("%3c", (char) (i-1 + 'a'));
        System.out.println("");
        for (int i = 1; i <= n; i++) {
            System.out.printf("%3c", (char) (i-1 + 'a'));
            for(int j = 1; j <= n; j++)
                if (G[i][j] != INF)
                    System.out.printf("%3d", G[i][j]);
                else
                    System.out.printf("%3d", "inf");
            System.out.println("");
        }        
    }
    int degree(int u) {
        int s = 0;
        for (int i = 1; i <= n; i++)
            s += G[u][i];
        return s;
    }
    int num_edges() {
        int s = 0;
        for (int i = 1; i <= n; i++) 
            s += degree(i);
        return s/2;
    }
    int count;
    char intToChar(int i) {
        return (char) (i - 1 + 'a');
    }
    boolean connected() {
        count = 0;
        dfs(1);
        return (count == n);
    }
    void dfsRecursion(int start) {
        visited[start] = true;
        System.out.print(" ---> " + intToChar(start));
        for(int i = 1; i <= n; i++)
            if(G[start][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfsRecursion(i);
            }        
    }
    void dfs(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        while(!stack.isEmpty()) {
            int current = stack.pop();
            System.out.print(" ---> " + intToChar(current));
            for (int i = n; i >= 1; i--)
                if(!visited[i] && G[current][i] == 1) {
                    visited[i] = true;
                    stack.push(i);
                }
                    
        }
        System.out.println("");
    }
    void bfsUsingRecursion(int start) {
        Queue<Integer> queue = new Queue<>();
        visited[start] = true;
        queue.enqueue(start);
        bfsRecursion(queue);
    }
    void bfsRecursion(Queue<Integer> queue) {
        if (queue.isEmpty())
            return;
        int current = queue.dequeue();
        System.out.print(" ---> " + intToChar(current));
        for (int i = 1; i <= n; i++)
            if(!visited[i] && G[current][i] == 1) {
                visited[i] = true;
                queue.enqueue(i);
            }
        bfsRecursion(queue);
    }
    void bfs(int start) {
        Queue<Integer> queue = new Queue<>();   
        queue.enqueue(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            for(int i = 1; i <= n; i++)
                if(!visited[i] && G[current][i] == 1) {
                    visited[i] = true;
                    queue.enqueue(i);
                }   
            System.out.print(" ---> " + intToChar(current));
        }
        System.out.println("");
    }
}
public class Main {
    public static void main(String[] args) {
        Graph G2 = new Graph();
        G2.display();
        G2.dfs(1);
        System.out.println(G2.connected() + " " + G2.count);
    }
    
}
