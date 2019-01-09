public class BinarySearchTree {
    public Node root;
    public BinarySearchTree(){
        this.root = null;
    }
    //탐색 연산
    public boolean find(int id){
        Node current = root;
        while(current!=null){
            //현재 노드와 찾는 값이 같으면
            if(current.getData()==id){
                return true;
                //찾는 값이 현재 노드보다 작으면
            } else if(current.getData()>id){
                current = current.getLeft();
                //찾는 값이 현재 노드보다 크면
            } else{
                current = current.getRight();
            }
        }
        return false;
    }

 public void insert(int id){
        Node newNode = new Node(id);
        if(root==null){
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while(true){
            parent = current;
            if(id < current.getData()){                
                current = current.getLeft();
                if(current==null){
                    parent.setLeft(newNode);
                    return;
                }
            }else{
                current = current.getRight();
                if(current==null){
                    parent.setRight(newNode);
                    return;
                }
            }
        }
    }


 //삭제 연산
    public boolean delete(int id){
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while(current.getData()!=id){
            parent = current;
            if(current.getData()>id){
                isLeftChild = true;
                current = current.getLeft();
            }else{
                isLeftChild = false;
                current = current.getRight();
            }
            if(current==null){
                return false;
            }
        }
        //Case 1: 자식노드가 없는 경우
        if(current.getLeft()==null && current.getRight()==null){
            if(current==root){
                root = null;
            }
            if(isLeftChild==true){
                parent.setLeft(null);
            }else{
                parent.setRight(null);
            }
        }

  //Case 2 : 하나의 자식을 갖는 경우
        else if(current.getRight()==null){
            if(current==root){
                root = current.getLeft();
            }else if(isLeftChild){
                parent.setLeft(current.getLeft());
            }else{
                parent.setRight(current.getLeft());
            }
        } else if(current.getLeft()==null){
            if(current==root){
                root = current.getRight();
            }else if(isLeftChild){
                parent.setLeft(current.getRight());
            }else{
                parent.setRight(current.getRight());
            }
        }

       //Case 3 : 두개의 자식을 갖는 경우
        else if(current.getLeft()!=null && current.getRight()!=null){
            // 오른쪽 서브트리의 최소값을 찾음
            Node successor = getSuccessor(current);
            if(current==root){
                root = successor;
            }else if(isLeftChild){
                parent.setLeft(successor);
            }else{
                parent.setRight(successor);
            }            
            successor.setLeft(current.getLeft());
        }        
        return true;        
    }
 
    public Node getSuccessor(Node deleleNode){
        Node successsor =null;
        Node successsorParent =null;
        Node current = deleleNode.getRight();
        while(current!=null){
            successsorParent = successsor;
            successsor = current;
            current = current.getLeft();
        }
        if(successsor!=deleleNode.getRight()){
            successsorParent.setLeft(successsor.getRight());
            successsor.setRight(deleleNode.getRight());
        }
        return successsor;
    }
