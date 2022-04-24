package algorithm;

class ListNode {
    int val;
    ListNode next;
    ListNode(){}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * 两数相加
 * 考察点：单向链表的结构，取模，取余，进位项
 */
public class Code_02 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head =null,tail = null;
        int carry = 0;

        while (l1!=null || l2!=null){
            int m = l1==null?0:l1.val;
            int n = l2==null?0:l2.val;
            int sum = m + n + carry;

            if(head == null){
                head = tail = new ListNode(sum%10);
            } else {
                tail.next = new ListNode(sum%10);
                tail = tail.next;
            }

            carry = sum/10;
            if(l1!=null){
                l1 = l1.next;
            }
            if(l2!=null){
                l2 = l2.next;
            }

        }

        if(carry>0){
            tail.next = new ListNode(carry);
            tail = tail.next;
        }

        return head;
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1,new ListNode(3));
        ListNode l2 = new ListNode(4,new ListNode(7));

        ListNode listNode = addTwoNumbers(l1, l2);

        while (listNode!=null){
            System.out.print(listNode.val + "\t");
            listNode= listNode.next;
        }
        System.out.println();
    }
}
